package pers.wpcap.aop;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.wpcap.exception.FilterException;
import pers.wpcap.helper.JoinPointToStringHelper;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: RateLimiterAspect
 * Created by haisong
 * Description:
 */
@Aspect
@Component
public class RateLimiterAspect {

    public interface KeyFactory {
        String createKey(JoinPoint joinPoint, RateLimit rateLimit);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterAspect.class);

    private static final KeyFactory DEFAULT_KEY_FACTORY =
            ((joinPoint, rateLimit) -> JoinPointToStringHelper.toString(joinPoint));

    private final Map<String, RateLimiter> limiters;
    private final KeyFactory keyFactory;

    @Autowired
    public RateLimiterAspect(Optional<KeyFactory> keyFactory) {
        this.limiters = new ConcurrentHashMap<>();
        this.keyFactory = keyFactory.orElse(DEFAULT_KEY_FACTORY);
    }

    @Before("@annotation(limit)")
    public void rateLimit(JoinPoint joinPoint, RateLimit limit) throws Exception {
        String key = createKey(joinPoint, limit);
        RateLimiter rateLimiter = limiters.computeIfAbsent(key, createLimiter(limit));
        boolean acquired = rateLimiter.tryAcquire();
        if (acquired) {
            LOGGER.info("Acquired rate limit permission ({} qps) for {}",
                    rateLimiter.getRate(), key);
        } else {
            throw new FilterException("qps exceeded, try after 1 sec");
        }
    }

    private Function<String, RateLimiter> createLimiter(RateLimit limit) {
        return name -> RateLimiter.create(limit.value());
    }

    private String createKey(JoinPoint joinPoint, RateLimit limit) {
        return Optional.ofNullable(Strings.emptyToNull(limit.key()))
                .orElseGet(() -> keyFactory.createKey(joinPoint, limit));
    }

}

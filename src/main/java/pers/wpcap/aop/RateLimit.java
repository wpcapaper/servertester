package pers.wpcap.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: RateLimit
 * Created by haisong
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * @return rate limit per second.
     */
    int value();

    /**
     * @return rate limiter identifier.
     */
    String key() default "";

}

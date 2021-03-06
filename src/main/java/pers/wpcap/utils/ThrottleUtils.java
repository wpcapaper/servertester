package pers.wpcap.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.wpcap.exception.FilterException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * Created with IntelliJ IDEA on 2017/6/16.
 * ClassName: ThrotteUtils
 * Created by haisong
 * Description:
 */
@Component
public class ThrottleUtils {

    private static final String TOKEN = "*";

    @Autowired
    private JedisPool jedisPool;

    public void throtteByLimit(String key, Long limit, Integer expire) throws Exception {

        Jedis jedis = jedisPool.getResource();

        long current = jedis.llen(key);

        if (current >= limit) {
            jedis.close();
            throw new FilterException("too many your requests. try again later.");
        }

        if (!jedis.exists(key)) {
            Transaction transaction = jedis.multi();
            transaction.rpush(key, TOKEN);
            transaction.expire(key, expire);
            transaction.exec();
        } else {
            jedis.rpushx(key,TOKEN);
        }

        jedis.close();

    }

}

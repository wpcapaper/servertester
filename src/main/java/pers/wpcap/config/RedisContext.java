package pers.wpcap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA on 2017/6/16.
 * ClassName: RedisContext
 * Created by haisong
 * Description:
 */
@Configuration
public class RedisContext {

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(1000);

        return new JedisPool(jedisPoolConfig,"localhost",6379,1000);
    }

}

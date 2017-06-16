package pers.wpcap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

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
        return new JedisPool("localhost",6379);
    }

}

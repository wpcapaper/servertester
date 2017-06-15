package pers.wpcap.aop;

import java.util.Map;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: RateLimitWithRedis
 * Created by haisong
 * Description:
 */
public @interface RateLimitWithRedis {

    int limit();

}

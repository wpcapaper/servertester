package pers.wpcap.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.wpcap.request.EchoRequest;
import pers.wpcap.request.RequestWrapper;
import pers.wpcap.utils.ClientUtils;
import pers.wpcap.utils.ThrottleUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: RequestFilterAspect
 * Created by haisong
 * Description:
 */
@Aspect
@Component
public class RequestFilterAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilterAspect.class);

    @Autowired
    private ThrottleUtils throttleUtils;

    @Pointcut("execution(* pers.wpcap.controller.SimpleController.echo(..))" +
            "&& args(httpServletRequest, request)")
    private void callMethod(HttpServletRequest httpServletRequest, EchoRequest request) {
    }

    @Before("callMethod(httpServletRequest, request)")
    public void filter(HttpServletRequest httpServletRequest, EchoRequest request) throws Throwable{

        String ip = ClientUtils.getClientFromRequest(httpServletRequest);

        LOGGER.info("request from ip:{}", ip);

        RequestWrapper requestWrapper = new RequestWrapper((long) 1 ,httpServletRequest);

        LOGGER.info("native request body:{}", requestWrapper.toByteArray());

        LOGGER.info("request body:{}", request.getCamelCase());

        throttleUtils.throtteByLimit(ip, (long) 2, 2);

    }
}

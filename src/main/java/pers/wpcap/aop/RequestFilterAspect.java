package pers.wpcap.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;
import pers.wpcap.request.EchoRequest;
import pers.wpcap.request.RequestWrapper;

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

    @Pointcut("execution(* pers.wpcap.controller.SimpleController.echo(..))" +
            "&& args(request)")
    private void callMethod(EchoRequest request) {

    }

    @Before("callMethod(request)")
    public void filter(EchoRequest request) throws Throwable{

        LOGGER.info("request body:{}", request.getCamelCase());

    }
}

package pers.wpcap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import pers.wpcap.aop.RateLimit;
import pers.wpcap.request.EchoRequest;
import pers.wpcap.utils.ClientUtils;


/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: SimpleController
 * Created by haisong
 * Description:
 */
@RestController
@RequestMapping("/v1")
public class SimpleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping(value = "/tester", method = RequestMethod.GET)
    @RateLimit(value = 1)
    public String simpleTester(HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        LOGGER.info("request from ip:{}", ClientUtils.getClientFromRequest(request));
        return format.format(date);
    }

    @RequestMapping(value = "/echo")
    public String echo(@RequestBody EchoRequest request) {
        return "value = " + request.getCamelCase().toString();
    }

}

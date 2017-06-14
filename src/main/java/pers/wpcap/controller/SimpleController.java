package pers.wpcap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String simpleTester(HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        LOGGER.info("request from ip:{}", getClientFromRequest(request));
        return format.format(date);
    }

    private String getClientFromRequest(HttpServletRequest request) {
        try {
            String remoteAddress = request.getHeader("X-Forwarded-For");
            if (isEffective(remoteAddress) && (remoteAddress.contains(","))) {
                String[] addresses = remoteAddress.split(",");
                for (String item: addresses) {
                    if (isEffective(item)) {
                        return item;
                    }
                }
            }
            if (!isEffective(remoteAddress)) {
                remoteAddress = request.getHeader("X-Real-IP");
            }
            if (!isEffective(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
            return remoteAddress;
        } catch (Exception e) {
            LOGGER.error("cannot get client address! {}", e.getMessage());
            return "";
        }
    }

    /**
     * judge addr is effective or not.
     * @param remoteAddress Address
     * @return boolean
     */
    private boolean isEffective(final String remoteAddress) {
        return  ((null != remoteAddress) && (!"".equals(remoteAddress.trim()))
                && (!"unknown".equalsIgnoreCase(remoteAddress.trim())));
    }

}

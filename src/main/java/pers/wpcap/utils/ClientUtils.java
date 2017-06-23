package pers.wpcap.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA on 2017/6/14.
 * ClassName: ClientUtils
 * Created by haisong
 * Description:
 */
public class ClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientUtils.class);

    /**
     * XFF not empty, get first effective;if cannot get, get XRI;if XRI empty, getRemoteAddr.
     * @param request http request
     * @return client ip
     */
    public static String getClientFromRequest(HttpServletRequest request) {
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
    private static boolean isEffective(final String remoteAddress) {
        return  ((null != remoteAddress) && (!"".equals(remoteAddress.trim()))
                && (!"unknown".equalsIgnoreCase(remoteAddress.trim())));
    }

}

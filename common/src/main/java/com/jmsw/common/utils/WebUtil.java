package com.jmsw.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jmsw.common.constant.AppConstants;
import com.jmsw.common.constant.DevType;
import com.jmsw.common.context.Context;
import com.jmsw.common.entity.RequestHeader;

/**
 * web信息获取工具
 *
 * @author danshiyu
 */
public class WebUtil {

    private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
		return ip;
    }

    public static String getLocalIp() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ip;
    }

    /**
     * 设置header
     *
     * @param params
     * @return
     */
    public static RequestHeader getHeader(HttpServletRequest request) {
        RequestHeader header = new RequestHeader();
        Method[] methods = header.getClass().getMethods();
        try {
            for (Method m : methods) {
                String methodName = m.getName();
                if (!methodName.startsWith("set")) {
                    continue;
                }
                methodName = methodName.substring(3);
                // 获取属性名称
                methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                if (methodName.equalsIgnoreCase("class")) {
                    continue;
                }
                String value = request.getHeader(methodName);

                m.invoke(header, new Object[]{value});
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            header.setDevType(DevType.IOS.getType());
        }
        header.setAppId(Context.getProperty(AppConstants.APP_ID));
        return header;
    }

}

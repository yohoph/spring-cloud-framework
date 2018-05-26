package com.jmsw.framework.core.context;

public class JmswContext {
    // TODO 考虑hystrix情况
    private static ThreadLocal<JmswRequestHeader> requestHeaderThreadLocal = new ThreadLocal<>();

    public static JmswRequestHeader getRequestHeader() {
        return requestHeaderThreadLocal.get();
    }

    public static void setRequestHeader(JmswRequestHeader requestHeader) {
        requestHeaderThreadLocal.set(requestHeader);
    }
}

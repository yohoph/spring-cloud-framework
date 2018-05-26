package com.jmsw.framework.core.exception;

import java.util.Map;

/**
 * 致命异常, 返回HTTP statusCode 500, 系统会发邮件短信告警
 */
public class JmswFatalException extends RuntimeException {
    private String tag;
    private Map<Object, Object> context;
    private Object[] args;

    public JmswFatalException(String tag, Object... args) {
        this.tag = tag;
        this.args = args;
    }

    public JmswFatalException(String tag, Map<Object, Object> context, Object[] args) {
        this.tag = tag;
        this.context = context;
        this.args = args;
    }
}

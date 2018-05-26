package com.jmsw.framework.core.exception;

import com.jmsw.framework.core.vo.Response;
import com.jmsw.framework.core.vo.ResponseConstant;

/**
 * 业务异常, 返回HTTP statusCode 200, body为构造参数, Response status=false
 */
public class JmswBusinessException extends RuntimeException {
    private Response response;

    public JmswBusinessException(String msg) {
        this.response = new Response(false, ResponseConstant.BUSY_EXCEPTION.getCode(), msg, msg);
    }
    
    public JmswBusinessException(String code, String msg) {
        this.response = new Response(false, code, msg, msg);
    }
    
    public JmswBusinessException(String code, String msg, String errorMsg, Object data) {
        this.response = new Response(false, code, msg, errorMsg, data);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

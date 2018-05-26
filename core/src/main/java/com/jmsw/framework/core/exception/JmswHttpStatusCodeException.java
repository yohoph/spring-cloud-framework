package com.jmsw.framework.core.exception;

import com.jmsw.framework.core.vo.Response;
import org.springframework.http.HttpStatus;

/**
 * 指定返回HTTP statusCode, 但返回body还是符合Response约束
 */
public class JmswHttpStatusCodeException extends RuntimeException {
    private Integer statusCode;
    private Response response;

    public JmswHttpStatusCodeException(int statusCode) {
        this.statusCode = statusCode;
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        this.response = new Response(httpStatus.is2xxSuccessful(), Integer.toString(httpStatus.value()), httpStatus.getReasonPhrase(), httpStatus.getReasonPhrase());
    }

    public JmswHttpStatusCodeException(int statusCode, Response response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

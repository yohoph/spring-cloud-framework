package com.jmsw.framework.core.exception;

import com.jmsw.framework.core.vo.Response;
import com.jmsw.framework.core.vo.ResponseConstant;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  全局统一异常处理 
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value={Exception.class})
	public Response<Object> exceptionHandler(Exception e) {
		Response<Object> resp =null;
		if(e instanceof JmswBusinessException){
			JmswBusinessException biz=(JmswBusinessException) e;
			resp=biz.getResponse();
		}
		else if(e instanceof JmswHttpStatusCodeException){
			JmswHttpStatusCodeException biz=(JmswHttpStatusCodeException) e;
			resp=biz.getResponse();
		}
		else{
			String msg=e.getMessage();
			if(StringUtils.isEmpty(msg)){
				msg=e.getLocalizedMessage();
			}
			msg=StringUtils.isEmpty(msg)? ResponseConstant.SYS_EXCEPTION.getShowMsg():msg;
			resp = new Response<Object>(false,ResponseConstant.SYS_EXCEPTION.getCode(),msg,msg);
		}
		return resp;
	}
	
}

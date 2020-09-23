package com.jmsw.framework.core.exception;

import com.jmsw.framework.core.vo.Response;
import com.jmsw.framework.core.vo.ResponseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${exception.logger.enable:false}")
	private boolean exceptionLoggerEnable;

	@Value("${exception.msg.error:false}")
	private boolean exceptionMsgMrror;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@ExceptionHandler(value={Exception.class})
	public Response<Object> exceptionHandler(Exception e) {
		if(exceptionLoggerEnable){
			logger.warn("handle exception",e);
		}
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
			if(exceptionMsgMrror){
				msg= ResponseConstant.SYS_EXCEPTION.getShowMsg();
			} else {
				msg=StringUtils.isEmpty(msg)? ResponseConstant.SYS_EXCEPTION.getShowMsg():msg;
			}
			resp = new Response<Object>(false,ResponseConstant.SYS_EXCEPTION.getCode(),msg,msg);
		}
		return resp;
	}
	
}

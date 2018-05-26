/*
 * Copyright (c) 2016-2018 Wuhan Jmsw Network Company LTD.
 */
package com.jmsw.framework.core.vo;

import com.jmsw.framework.core.exception.JmswBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 返回对象工具
 *
 * @author pxie
 * @version 1.0
 * @data 2017/10/30 0030 50
 */
public class ResponseUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    /**
     * 返回成功
     *
     * @return
     */
    public static <T> Response<T> returnSuccess() {
        return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", null);
    }

    /**
     * 返回对象结果
     *
     * @param t
     * @return
     */
    public static <T> Response<T> returnObjectSuccess(T t) {
        if (t == null) {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", null);
        } else {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", t);
        }
    }

    /**
     * 返回对象结果
     *
     * @param t
     * @return
     */
    public static <T> Response<T> returnApiObjectSuccess(T t) {
        if (t == null) {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", new HashMap<>());
        } else {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", t);
        }
    }

    /**
     * 返回列表结果
     *
     * @param collection
     * @return
     */
    public static <T> Response<T> returnListSuccess(Collection<?> collection) {
        if (collection == null) {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", new ArrayList());
        } else {
            return new Response(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", collection);
        }
    }

    /**
     * 返回异常信息
     *
     * @param e
     * @return
     */
    public static <T> Response<T> returnException(Exception e) {

        if (e instanceof JmswBusinessException) {
            JmswBusinessException qe = (JmswBusinessException) e;
            return qe.getResponse();
        } else if (e instanceof IllegalArgumentException) {
            IllegalArgumentException ll = (IllegalArgumentException) e;
            return new Response<T>(false, ResponseConstant.VALIDATE_EXCEPTION.getCode(), ResponseConstant.VALIDATE_EXCEPTION.getShowMsg(), ll.getMessage() + " [Exception]:" + e, null);
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException msrp = (MissingServletRequestParameterException) e;
            return new Response<T>(false, ResponseConstant.VALIDATE_EXCEPTION.getCode(), ResponseConstant.VALIDATE_EXCEPTION.getShowMsg(), msrp.getMessage() + " [Exception]:" + e, null);
        } else {
            logger.error(e.getMessage(), e);
            return new Response<T>(false, ResponseConstant.EXCEPTION.getCode(), ResponseConstant.EXCEPTION.getShowMsg(), ResponseConstant.EXCEPTION.getErrorMsg() + " [Exception]:" + e, null);
        }
    }

    /**
     * 简化消息提示，客户端得到该错误只会直接显示消息内容
     *
     * @param msg
     * @return
     */
    public static <T> Response<T> returnCommonException(String msg) {
        return new Response<T>(false, ResponseConstant.SYS_EXCEPTION.getCode(), msg, msg, null);
    }

    /**
     * @param  res
     * @return T
     * @throws
     * @Description: 获取返回对象中对象
     * @author jk
     */
    public static <T> T getResponseData(Response<T> res) {
        if (null == res) {
            throw new JmswBusinessException(ResponseConstant.SYS_EXCEPTION.getCode(),"response object must not null","response object must not null",null);
        }

        if (!res.success() || !ResponseConstant.SUCCESS.getCode().equals(res.getCode())) {
            throw new JmswBusinessException(res.getCode(), res.getMsg(), res.getErrorMsg(),res.getData());
        }

        return res.getData();
    }
}

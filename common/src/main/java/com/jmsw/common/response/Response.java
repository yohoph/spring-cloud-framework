///*
// * Copyright (c) 2016-2018 Wuhan Yryz Network Company LTD.
// */
//package com.jmsw.common.response;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
//import java.io.Serializable;
//
///**
// * @Description: rpc返回数据订阅
// * @author suyongcheng
// * @date 2017年10月27日15:08:03
// * @version 1.0
// */
//@ApiModel("结果返回")
//public class Response<T> implements Serializable {
//
//	@ApiModelProperty(value="请求结果,boolean型")
//	protected Boolean status;
//
//	@ApiModelProperty(value="请求状态,200成功，100系统异常")
//	protected String code = ResponseConstant.SUCCESS.getCode();
//
//	@ApiModelProperty(value="系统消息")
//	protected String msg = ResponseConstant.SUCCESS.getShowMsg();
//
//	@ApiModelProperty(value="错误消息")
//	protected String errorMsg = ResponseConstant.SUCCESS.getErrorMsg();
//
//	@ApiModelProperty(value="结果集")
//	protected T data;
//
//    /**
//	 * @param status 请求结果
//	 * @param code  请求状态
//	 * @param msg   系统消息
//	 * @param errorMsg 错误信息
//	 * @exception
//	 */
//	public Response(Boolean status, String code, String msg, String errorMsg) {
//		super();
//		this.status = status;
//		this.code = code;
//		this.msg = msg;
//		this.errorMsg = errorMsg;
//	}
//
//	/**
//	 * @param data 结果集
//	 * @exception
//	 */
//	public Response(T data) {
//		super();
//		this.data = data;
//	}
//
//	/**
//	 * @param status 请求结果
//	 * @param code  请求状态
//	 * @param msg   系统消息
//	 * @param errorMsg 错误信息
//	 * @param data 结果集
//	 * @exception
//	 */
//	public Response(Boolean status, String code, String msg, String errorMsg, T data) {
//		super();
//		this.status = status;
//		this.code = code;
//		this.msg = msg;
//		this.errorMsg = errorMsg;
//		this.data = data;
//	}
//
//	/**
//	 *  默认构造函数
//	 */
//	public Response() {
//		super();
//	}
//
//	/**
//     * 调用结果
//     * @return boolean
//     */
//    public Boolean success(){
//    	return status;
//    }
//
//    /**
//     * 错误编码
//     * @return
//     */
//    public String getCode(){
//    	return code;
//    }
//
//    /**
//     * 业务提示信息
//     * @return
//     */
//    public String getMsg(){
//    	return msg;
//    }
//
//    /**
//     * 错误信息
//     * @return
//     */
//    public String getErrorMsg(){
//    	return errorMsg;
//    }
//
//    /**
//     * 实体数据
//     * @return
//     */
//    public T getData(){
//    	return data;
//    }
//
//	/**
//	 * toString
//	 * @return
//	 * @see Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "Response [status=" + status + ", code=" + code + ", msg=" + msg + ", errorMsg=" + errorMsg + ", data="
//				+ data + "]";
//	}
//
//	/**
//	 * hashCode
//	 * @return
//	 * @see Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((code == null) ? 0 : code.hashCode());
//		result = prime * result + ((data == null) ? 0 : data.hashCode());
//		result = prime * result + ((errorMsg == null) ? 0 : errorMsg.hashCode());
//		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
//		result = prime * result + ((status == null) ? 0 : status.hashCode());
//		return result;
//	}
//
//	/**
//	 * equals
//	 * @param obj
//	 * @return
//	 * @see Object#equals(Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj){
//			return true;
//		}
//		if (obj == null){
//			return false;
//		}
//		if (getClass() != obj.getClass()){
//			return false;
//		}
//		Response<?> other = (Response<?>) obj;
//		if (code == null) {
//			if (other.code != null){
//				return false;
//			}
//		} else if (!code.equals(other.code)){
//			return false;
//		}
//		if (data == null) {
//			if (other.data != null){
//				return false;
//			}
//		} else if (!data.equals(other.data)){
//			return false;
//
//		}
//		if (errorMsg == null) {
//			if (other.errorMsg != null){
//				return false;
//			}
//		} else if (!errorMsg.equals(other.errorMsg)){
//			return false;
//		}
//		if (msg == null) {
//			if (other.msg != null){
//				return false;
//			}
//		} else if (!msg.equals(other.msg)){
//			return false;
//		}
//		if (status == null) {
//			if (other.status != null){
//				return false;
//			}
//		} else if (!status.equals(other.status)){
//			return false;
//		}
//		return true;
//	}
//
//}

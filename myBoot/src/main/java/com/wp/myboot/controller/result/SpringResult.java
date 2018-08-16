/**
* @Company 青鸟软通   
* @Title: ScoreResult.java 
* @Package com.haier.isales.common 
* @author Liu Wenjie   
* @date 2014-11-24 下午1:48:43 
* @version V1.0   
*/ 
package com.wp.myboot.controller.result;

import java.io.Serializable;

/** 
 * @ClassName: ScoreResult 
 * @Description: service层带积分的返回结果对象
 *  
 */
public class SpringResult implements Serializable {

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = -2406163386008897757L;

	/** 
	* @Fields result : 原有的业务处理返回的结果对象
	*/ 
	private Object result;
	
	private String resultCode;	//返回结果编码
	
	private String errorStackTrack;	//返回结果的堆栈信息
	
	private String message;
	
	/** 
	* @Fields scoreList : 积分结果对象
	*/ 

	/** 
	* <p>Description: </p> 
	* @author Guo Yuchao   
	* @date 2014-11-24 下午4:56:10  
	*/ 
	public SpringResult() {
		super();
	}
	public SpringResult(Object result) {
		super();
		this.result = result;
	}
	

	/**
	 * @Description: 属性 result 的get方法 
	 * @return result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @Description: 属性 result 的set方法 
	 * @param result 
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrorStackTrack() {
		return errorStackTrack;
	}
	public void setErrorStackTrack(String errorStackTrack) {
		this.errorStackTrack = errorStackTrack;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ServiceResult [result=" + result + ", resultCode=" + resultCode
				+ ", errorStackTrack=" + errorStackTrack + ", message="
				+ message + "]";
	}
}

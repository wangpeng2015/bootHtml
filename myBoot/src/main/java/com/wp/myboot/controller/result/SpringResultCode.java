/**
* @Company 青鸟软通   
* @Title: DubboResultCode.java 
* @Package com.haier.isales.common 
* @author Cao Rui   
* @date 2014-11-10 下午7:17:52 
* @version V1.0   
*/ 
package com.wp.myboot.controller.result;

import java.io.Serializable;


/** 
 * @ClassName: DubboResultCode 
 * @Description: DubboResult的返回结果编码定义
 *  
 */
public class SpringResultCode implements Serializable {

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = -56723683112L;
	public static final String SUCCESS = "200";//接口业务处理完成，无业务或者系统异常，返回成功编码

	public static final String NO_LOGIN = "103";//没有登录
	public static final String NO_AUTH = "403";//没有权限
	public static final String BUSINESS_ERROR = "300"; //业务异常
	public static final String SYSTEM_ERROR = "400"; //系统异常
	public static final String RUNTIME_ERROR = "500"; //运行异常
	public static final String ERROR = "900";//接口业务处理发生无业务或者系统异常，返回失败编码；待完善


}

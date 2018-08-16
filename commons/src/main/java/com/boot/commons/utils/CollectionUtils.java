package com.boot.commons.utils;


/**
 * <pre>
 * 类名中文描述: 对集合进行操作的工具类，继承了 spring CollectionUtils
 *
 * 基本操作功能:
 *
 * Module ID  : srplatform-1.1.1 
 *
 * Create Date：2012-3-31 下午05:10:30
 * 
 * CopyRight  :  Copyright(C) 2008-xxxx  珠海政采软件技术有限公司 <br/>
 * 
 * @since 0.1
 * @version: 0.1
 * @author <a href="mailto:kehl@gpcsoft.com">kehl</a>
 *
 * Change History Log
 * --------------------------------------------------------------------------------------------------------------
 * Date	      | Version | Author	   | Description			              
 * --------------------------------------------------------------------------------------------------------------
 * 2012-3-31 | 0.1     | kevin| CREATE THE JAVA FILE: CollectionUtils.java.
 * --------------------------------------------------------------------------------------------------------------
 *
 * --------------------------------------------------------------------------------------------------------------
 *
 * </pre>
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils
{
	public static String[] cloneSubarray(String a[], int from, int to)
	{
		int n = to - from;
		String result[] = new String[n];
		System.arraycopy(a, from, result, 0, n);
		return result;
	}
}
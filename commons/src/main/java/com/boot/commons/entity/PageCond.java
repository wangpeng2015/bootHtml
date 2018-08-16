/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boot.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liuwenjie
 */
public class PageCond implements Serializable {
    /** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 2920009265593779873L;
	private int totalCount;   //结果集总条数
    private int pageSize;     //每页显示条数
    private int currentPage;  //当前第几页
    private int pageCount;    //总页数
    
    /*======计算出的属性值==========*/
    private int firstResult; //分页查询时对应的第一条记录值

    private Map<String,BigDecimal> sumMap;//保存一些加和的属性（key是列名，value是加和的值）
    public PageCond() {
    }

    public PageCond(int currentPage, int pageSize) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
    
    

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

	/**
	 * @Description: 属性 sumMap 的get方法 
	 * @return sumMap
	 */
	public Map<String,BigDecimal> getSumMap() {
		if(sumMap == null){
			sumMap = new HashMap<String, BigDecimal>();
		}
		return sumMap;
	}

	/**
	 * @Description: 属性 sumMap 的set方法 
	 * @param sumMap 
	 */
	public void setSumMap(Map<String,BigDecimal> sumMap) {
		this.sumMap = sumMap;
	}

	/**
	 * @Description: 属性 firstResult 的get方法 
	 * @return firstResult
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * @Description: 属性 firstResult 的set方法 
	 * @param firstResult 
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
    
}

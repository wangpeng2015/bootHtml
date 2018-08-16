/**
 * @Company 青鸟软通   
 * @Title: CommonMapper.java
 * @Package  com.haier.isales.common.dao
 * @author Liu Wenjie   
 * @date 2014年11月15日  14:58:34
 * @version V1.0   
 */
package com.boot.commons.entity;


import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/** 
 * @ClassName: CommonDAO
 * @Description: 通用的DAO接口定义类
 *  
 */
public interface CommonMapper<T,ID extends Serializable> {
	
	/** 
	* @Description: 根据ID查询对应的实体信息
	* @author Liu Wenjie   
	* @date 2014年11月15日  14:58:34
	* @param id
	* @return  
	*/ 
	public T findById(ID id);
	
	/** 
	* @Description: 查询实体对应的所有数据量
	* @author Liu Wenjie   
	* @date 2014年11月15日  14:58:34
	* @return  
	*/  
	public Long count();
	
	/** 
	* @Description: 保存一条实体的方法
	* @author Liu Wenjie   
	* @date 2014年11月15日  14:58:34 
	* @param entity
	* @return  
	*/ 
	public int save(T entity);
	
	/** 
	* @Description: 更新一条实体的方法，是按照主键进行更新的方法
	* @author Liu Wenjie    
	* @date 2014年11月15日  14:58:34  
	* @param entity
	* @return  
	*/ 
	public int update(T entity);
	
	/** 
	* @Description: 按照主键去删除一条记录
	* @author Liu Wenjie   
	* @date 2014年11月15日  14:58:34 
	* @param id
	* @return  
	*/ 
	public int delete(ID id);
	
	/** 
	* @Description: 按照实体中的对象参数进行模糊查询匹配
	* @author Liu Wenjie   
	* @date 2014年11月15日  14:58:34 
	* @param entity
	* @return  
	*/ 
	public List<T> findByParams(@Param("entity") T entity, @Param("pageCond") PageCond pageCond);

	/**
	* @Description: 按照实体中的对象参数进行模糊查询匹配
	* @author Liu Wenjie
	* @date 2014年11月15日  14:58:34
	* @param entity
	* @return
	*/
	public int findCountByParams(@Param("entity") T entity);
}
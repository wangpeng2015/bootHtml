package com.boot.commons.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础bean
 * <P>File name : BaseEntity.java </P>
 * <P>Author :  </P>
 * <P>Date : 2017年12月5日 </P>
 */
public class BaseEntity implements Serializable {

    /**
     * 字段或域定义：<code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5838432387278762425L;
    /**
     * 操作者 IP
     */
    protected String ip;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 创建者
     */
    protected String creator;
    /**
     * 修改时间
     */
    protected Date modifyTime;
    /**
     * 修改者
     */
    protected String modifier;
    /**
     * 版本号
     */
    protected Long version;
    /**
     * 当前页码
     */
    protected Integer pageNo;
    /**
     * 每页的记录数量
     */
    protected Integer pageSize;
    /**
     * 数据查询开始行
     */
    protected Integer startNum;
    /**
     * 数据查询结束行
     */
    protected Integer limitNum;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }
}

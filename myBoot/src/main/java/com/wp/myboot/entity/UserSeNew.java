package com.wp.myboot.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *
 *  Created by wangpeng on '2018-08-03 13:10:22'.
 */
public class UserSeNew implements Serializable {
    private static final long serialVersionUID = 42L;

    /**
     *
     */
    private int id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     * 设备的uid
     */
    private String uid;

    /**
     *
     */
    private String tuijianCode;

    /**
     *
     */
    private Date createDate;

    /**
     *
     */
    private int flag;

    /**
     *
     */
    private String remarks;

    /**
     *
     */
    private BigDecimal rechargeMoney;

    /**
     *
     */
    private Date rechargeMoneyEndDate;

    /**
     *
     */
    private Date rechargeMoneyUpdateDate;

    /**
     *
     */
    private int yearMonth;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTuijianCode() {
        return tuijianCode;
    }

    public void setTuijianCode(String tuijianCode) {
        this.tuijianCode = tuijianCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getRechargeMoneyEndDate() {
        return rechargeMoneyEndDate;
    }

    public void setRechargeMoneyEndDate(Date rechargeMoneyEndDate) {
        this.rechargeMoneyEndDate = rechargeMoneyEndDate;
    }

    public Date getRechargeMoneyUpdateDate() {
        return rechargeMoneyUpdateDate;
    }

    public void setRechargeMoneyUpdateDate(Date rechargeMoneyUpdateDate) {
        this.rechargeMoneyUpdateDate = rechargeMoneyUpdateDate;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(int yearMonth) {
        this.yearMonth = yearMonth;
    }

}

package com.reserve.pojo;

import java.util.Date;

public class ReserveInfo {
    private Integer id;

    private Integer userId;

    private String userRealname;

    private Integer instId;

    private String instName;

    private Date testStartTime;

    private Date testEndTime;

    private Integer enableStatus;

    private Byte isCompleted;

    private Date createTime;

    private Date updateTime;

    public ReserveInfo(Integer id, Integer userId, String userRealname, Integer instId, String instName, Date testStartTime, Date testEndTime, Integer enableStatus, Byte isCompleted, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.userRealname = userRealname;
        this.instId = instId;
        this.instName = instName;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.enableStatus = enableStatus;
        this.isCompleted = isCompleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ReserveInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    public Date getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(Date testStartTime) {
        this.testStartTime = testStartTime;
    }

    public Date getTestEndTime() {
        return testEndTime;
    }

    public void setTestEndTime(Date testEndTime) {
        this.testEndTime = testEndTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Byte getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Byte isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
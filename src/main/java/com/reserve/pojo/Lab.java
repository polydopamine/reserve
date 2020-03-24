package com.reserve.pojo;

import java.util.Date;

public class Lab {
    private Integer id;

    private String name;

    private Integer departId;

    private Integer instrutorId;

    private String departName;

    private Date createTime;

    private Date updateTime;

    public Lab(Integer id, String name, Integer departId, Integer instrutorId, String departName, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.departId = departId;
        this.instrutorId = instrutorId;
        this.departName = departName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Lab() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Integer instrutorId) {
        this.instrutorId = instrutorId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
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
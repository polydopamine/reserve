package com.reserve.pojo;

import java.util.Date;

public class Instrument {
    private Integer id;

    private String name;

    private String model;

    private String location;

    private String description;

    private String image;

    private Integer labId;

    private Integer enableStatus;

    private Date createTime;

    private Date updateTime;

    public Instrument(Integer id, String name, String model, String location, String description, String image, Integer labId, Integer enableStatus, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.location = location;
        this.description = description;
        this.image = image;
        this.labId = labId;
        this.enableStatus = enableStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Instrument() {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
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
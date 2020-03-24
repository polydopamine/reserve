package com.reserve.vo;

import java.util.List;

public class InstrumentVo {

    private Integer id;

    private String name;

    private String model;

    private String location;

    private String image;

    private Integer labId;

    private String labName;

    private Integer enableStatus;

    private String description;

    // 未完成的测试列表
    private List<ReserveVo> uncompletedReserveList;

    // 所有的测试列表
    private List<ReserveVo> reserveList;

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
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLabId() {
        return labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ReserveVo> getUncompletedReserveList() {
        return uncompletedReserveList;
    }

    public void setUncompletedReserveList(List<ReserveVo> uncompletedReserveList) {
        this.uncompletedReserveList = uncompletedReserveList;
    }

    public List<ReserveVo> getReserveList() {
        return reserveList;
    }

    public void setReserveList(List<ReserveVo> reserveList) {
        this.reserveList = reserveList;
    }
}

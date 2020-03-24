package com.reserve.vo;

import com.reserve.pojo.Lab;

import java.util.List;

public class UserVo {

    // 用户表id
    private Integer id;

    // 昵称
    private String username;

    // 昵称
    private String realname;

    // 用户身份1-老师2-博后3-工作人员4-博士5-硕士6-本科生
    private Integer role;

    // 入学年份
    private Integer enrollmentYear;

    // 学号或工号
    private String studentId;

    // 电话号码
    private String phone;

    // 邮箱
    private String email;

    // 用户状态0-超级管理员1-实验室管理员2-正常使用-1-禁用
    private Integer enableRole;

    // 用户所属课题组
    private Lab lab;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEnableRole() {
        return enableRole;
    }

    public void setEnableRole(Integer enableRole) {
        this.enableRole = enableRole;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
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

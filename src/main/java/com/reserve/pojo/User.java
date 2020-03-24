package com.reserve.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private Integer role;

    private Integer enrollmentYear;

    private Integer labId;

    private Integer instrutorId;

    private String studentId;

    private String phone;

    private String email;

    private Integer enableRole;

    private Date createTime;

    private Date updateTime;

    public User(Integer id, String username, String realname, String password, Integer role, Integer enrollmentYear, Integer labId, Integer instrutorId, String studentId, String phone, String email, Integer enableRole, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.role = role;
        this.enrollmentYear = enrollmentYear;
        this.labId = labId;
        this.instrutorId = instrutorId;
        this.studentId = studentId;
        this.phone = phone;
        this.email = email;
        this.enableRole = enableRole;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

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
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public Integer getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Integer instrutorId) {
        this.instrutorId = instrutorId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getEnableRole() {
        return enableRole;
    }

    public void setEnableRole(Integer enableRole) {
        this.enableRole = enableRole;
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
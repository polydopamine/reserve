package com.reserve.common;

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role {
        int ROLE_INSTRUSTOR = 1; // 老师
        int ROLE_POSTDOC = 2; // 博后
        int ROLE_EMPLOYEE = 3; // 工作人员
        int ROLE_DOCTOR = 4; // 博士
        int ROLE_MASTER = 5; // 硕士
        int ROLE_UNDERGRADUATE = 6; // 本科生
    }

    public interface EnableRole {
        int ROLE_SUPER_ADMIN = 0; // 超级管理员
        int ROLE_ADMIN = 1; // 实验室管理员
        int ROLE_VALID = 2; // 普通用户
        int ROLE_FORBIDDEN = -1; // 实验室管理员
    }
}

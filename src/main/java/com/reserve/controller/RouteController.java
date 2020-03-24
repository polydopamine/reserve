package com.reserve.controller;

import com.reserve.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.security.provider.MD5;

import java.util.Date;

@Controller
public class RouteController {

//    登陆页面
    @RequestMapping("")
    public String mainPage() {
        return "portal/user-login";
    }

//    登陆页面
    @RequestMapping("/user/login")
    public String userLogin() {
        return "portal/user-login";
    }

//    注册页面
    @RequestMapping("/user/register")
    public String userRegister() {
        return "portal/user-register";
    }

//    忘记密码
    @RequestMapping("/user/reset_password")
    public String userResetPassword() {
        return "portal/user-reset-password";
    }

//    编辑用户信息
    @RequestMapping("/user/edit")
    public String userInfoEdit() {
        return "portal/user-info-edit";
    }

//    查看所有预约信息
    @RequestMapping("/reserve/list")
    public String reserveList() {
        return "portal/reserve-list";
    }

//    编辑预约信息页面
    @RequestMapping("/reserve/add")
    public String reserveAdd() {
        return "portal/reserve-add";
    }

//    编辑预约信息页面
    @RequestMapping("/reserve/edit")
    public String reserveEdit() {
        return "portal/reserve-edit";
    }

//    编辑预约信息页面
    @RequestMapping("/inst/list")
    public String instrumentList() {
        return "portal/instrument-list";
    }

//    编辑预约信息页面
    @RequestMapping("/inst/detail")
    public String instrumentDetail() {
        return "portal/instrument-detail";
    }

    //    编辑预约信息页面
    @RequestMapping("/error")
    public String error() {
        return "portal/error-page";
    }

//    public static void main(String[] args) {
//        String s1 = new String("aa");
////        s1.intern();
//        String s2 = "a` a";
//        String s22 = "aa";
//        System.out.println(s1 == s2);
//        System.out.println(s2 == s22);
//        String s3 = new String("bb") + new String("cc");
//        s3.intern();
//        String s4 = "b" + "bcc";
//        System.out.println(s3 == s4);
//    }

    public static void main(String[] args) {
        String password = "admin";
        System.out.println(MD5Util.MD5EncodeUtf8(password));
    }
}
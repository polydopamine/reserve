package com.reserve.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminRouteController {
    //    登陆页面
    @RequestMapping("/user/list")
    public String userList() {
        return "backend/user-list";
    }

    //    登陆页面
    @RequestMapping("/user/edit")
    public String userEdit() {
        return "backend/user-info-edit";
    }

    //    登陆页面
    @RequestMapping("/inst/list")
    public String instrumentList() {
        return "backend/instrument-list";
    }

    //    登陆页面
    @RequestMapping("/inst/edit")
    public String instrumentEdit() {
        return "backend/instrument-edit";
    }

    //    登陆页面
    @RequestMapping("/inst/add")
    public String instrumentAdd() {
        return "backend/instrument-add";
    }

    //    登陆页面
    @RequestMapping("/lab/add")
    public String labAdd() {
        return "backend/lab-add";
    }

    //    登陆页面
    @RequestMapping("/lab/list")
    public String labEdit() {
        return "backend/lab-list";
    }

}

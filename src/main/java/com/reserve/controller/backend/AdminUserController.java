package com.reserve.controller.backend;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.User;
import com.reserve.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user/")
public class AdminUserController {

    @Autowired
    private IUserService iUserService;

    // 后台管理员登陆
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(HttpSession session, String username, String password) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            // 如果是普通管理员或超级管理员，则将User添加到session中
            if (user.getEnableRole() == Const.EnableRole.ROLE_ADMIN || user.getEnableRole() == Const.EnableRole.ROLE_SUPER_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, user);
            } else {
                return ServerResponse.createByErrorMessage("没有管理员权限");
            }
        }
        return response;
    }

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    @ResponseBody
    // 返回所有用户列表，超级管理员可以对所有用户进行操作，普通管理员仅能对自己管理的实验室进行操作
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user.getEnableRole() != Const.EnableRole.ROLE_ADMIN && user.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有管理员权限");
        }
        return iUserService.list(user, pageNum, pageSize);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    // 对用户的信息进行修改
    public ServerResponse update(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser.getEnableRole() != Const.EnableRole.ROLE_ADMIN && currentUser.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有管理员权限");
        }
        return iUserService.adminUpdate(user);
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    // 对用户的信息进行修改
    public ServerResponse add(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (!currentUser.getUsername().equals("wsh")) {
            return ServerResponse.createByErrorMessage("没有管理员权限");
        }
        return iUserService.add(user);
    }

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    // 对用户的信息进行修改
    public ServerResponse getUserInfo(HttpSession session, Integer userId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (!currentUser.getUsername().equals("wsh")) {
            return ServerResponse.createByErrorMessage("没有管理员权限");
        }
        return iUserService.detail(userId);
    }
}

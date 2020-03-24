package com.reserve.controller.portal;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ResponseCode;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.User;
import com.reserve.service.IUserService;
import com.reserve.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    // 用户登录
    public ServerResponse<User> login(HttpSession session, String username, String password) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            session.setAttribute(Const.CURRENT_USER, user);
        }
        return response;
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    // 用户注册
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    // 检查字段是否有效，type = "phone" or "email"
    public ServerResponse checkValid(String type, String str) {
        return iUserService.checkValid(type, str);
    }

    @RequestMapping(value = "get_user_info.do")
    @ResponseBody
    // 获取当前用户信息
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    // 更新当前用户信息
    public ServerResponse update(HttpSession session, User updateUser) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        // 防止横向越权
        updateUser.setId(currentUser.getId());
        ServerResponse response = iUserService.update(currentUser, updateUser);
        if (response.isSuccess()) {
            // 将更新后的用户信息放入session
            session.setAttribute(Const.CURRENT_USER, updateUser);
        }
        return response;
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    // 重设密码
    public ServerResponse resetPassword(String username, String passwordOld, String passwordNew) {
        return iUserService.resetPassword(username, passwordOld, passwordNew);
    }

    @RequestMapping(value = "logout.do")
    // 注销登录
    public String logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        // 注销后返回到对应的html
        return "portal/user-login";
    }

    @RequestMapping(value = "detail.do")
    @ResponseBody
    // 返回用户的详细信息，包括实验室信息，该用户的所有预约，未完成的预约
    public ServerResponse<UserVo> detail(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.detail(user.getId());
    }
}

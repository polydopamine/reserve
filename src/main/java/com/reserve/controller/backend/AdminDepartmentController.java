package com.reserve.controller.backend;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.Department;
import com.reserve.pojo.Lab;
import com.reserve.pojo.User;
import com.reserve.service.IDepartmentService;
import com.reserve.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/depart/")
public class AdminDepartmentController {
    @Autowired
    private IDepartmentService iDepartmentService;

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    // 返回课题组列表
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有超级管理员权限");
        }
        return iDepartmentService.list(pageNum, pageSize);
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    // 新增课题组的信息
    public ServerResponse add(HttpSession session, Department department) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有超级管理员权限");
        }
        return iDepartmentService.add(department);
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    @ResponseBody
    // 对课题组的信息进行修改
    public ServerResponse update(HttpSession session, Department department) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有超级管理员权限");
        }
        return iDepartmentService.update(department);
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    // 删除课题组的信息
    public ServerResponse update(HttpSession session, Integer departId) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有超级管理员权限");
        }
        return iDepartmentService.delete(departId);
    }
}

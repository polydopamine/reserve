package com.reserve.controller.backend;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ResponseCode;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.ReserveInfo;
import com.reserve.pojo.User;
import com.reserve.service.IInstrumentService;
import com.reserve.service.IReserveService;
import com.reserve.vo.ReserveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/reserve/")
public class AdminReserveController {

    @Autowired
    private IReserveService iReserveService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> listInstrument(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user.getEnableRole() != Const.EnableRole.ROLE_ADMIN && user.getEnableRole() != Const.EnableRole.ROLE_SUPER_ADMIN) {
            return ServerResponse.createByErrorMessage("没有管理员权限");
        }
        return iReserveService.list(user, pageNum, pageSize);
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, ReserveVo reservevo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.add(user.getId(), reservevo);
    }
    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, ReserveVo reservevo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.update(user.getId(), reservevo);
    }
    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse delete(HttpSession session, Integer reserveId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.delete(user.getId(), reserveId);
    }
}

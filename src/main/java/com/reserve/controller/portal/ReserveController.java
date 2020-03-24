package com.reserve.controller.portal;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ResponseCode;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.ReserveInfo;
import com.reserve.pojo.User;
import com.reserve.service.IReserveService;
import com.reserve.vo.ReserveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserve/")
public class ReserveController {

    @Autowired
    private IReserveService iReserveService;

    @RequestMapping("list.do")
    @ResponseBody
    // 获取当前用户的所有预约信息的列表
    public ServerResponse<PageInfo> list(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.list(user, pageNum, pageSize);
    }
    @RequestMapping("add.do")
    @ResponseBody
    // 添加预约信息
    public ServerResponse add(HttpSession session, ReserveVo reserveVo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.add(user.getId(), reserveVo);
    }
    @RequestMapping("update.do")
    @ResponseBody
    // 更新预约信息
    public ServerResponse update(HttpSession session, ReserveVo reserveVo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.update(user.getId(), reserveVo);
    }

    @RequestMapping("delete.do")
    // 删除预约信息
    // 删除后返回到当前用户所有预约的界面
    public String delete(HttpSession session, Integer reserveId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return "portal/user-login";
        }
        iReserveService.delete(user.getId(), reserveId);
        return "portal/reserve-list";
    }

    @RequestMapping("detail.do")
    @ResponseBody
    // 返回特定预约信息的详细信息
    public ServerResponse<ReserveVo> detail(HttpSession session, Integer reserveId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iReserveService.detail(user.getId(), reserveId);
    }

    @RequestMapping("complete.do")
    // 标记预约已完成
    public String complete(HttpSession session, Integer reserveId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return "portal/user-login";
        }
        iReserveService.complete(user.getId(), reserveId);
        return "portal/reserve-list";
    }
}

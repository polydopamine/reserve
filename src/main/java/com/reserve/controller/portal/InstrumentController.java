package com.reserve.controller.portal;

import com.github.pagehelper.PageInfo;
import com.reserve.common.Const;
import com.reserve.common.ResponseCode;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.User;
import com.reserve.vo.InstrumentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.reserve.pojo.Instrument;
import com.reserve.service.IInstrumentService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/inst/")
public class InstrumentController {

    @Autowired
    private IInstrumentService iInstrumentService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> listInstrument(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iInstrumentService.list(user, pageNum, pageSize);
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<InstrumentVo> detail(HttpSession session, Integer instId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iInstrumentService.detail(instId);
    }
}

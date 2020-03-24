package com.reserve.service;

import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.ReserveInfo;
import com.reserve.pojo.User;
import com.reserve.vo.ReserveVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IReserveService {

    ServerResponse<PageInfo> list(User user, int pageNum, int pageSize);

    ServerResponse add(Integer userId, ReserveVo reserveVo);

    ServerResponse update(Integer userId, ReserveVo reserveVo);

    ServerResponse delete(Integer userId, Integer reserveId);

    ServerResponse<ReserveVo> detail(Integer userId, Integer reserveId);

    ServerResponse complete(Integer userId, Integer reserveId);

    List<ReserveVo> queryReserveListByInstId(int instId);

    List<ReserveVo> queryUncompletedReserveListByInstId(int instId);

    List<ReserveVo> queryReserveListByUserId(int userId);

    List<ReserveVo> queryUncompletedReserveListByUserId(int userId);
}

package com.reserve.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.reserve.common.ServerResponse;
import com.reserve.dao.ReserveInfoMapper;
import com.reserve.pojo.Instrument;
import com.reserve.pojo.ReserveInfo;
import com.reserve.pojo.User;
import com.reserve.service.IInstrumentService;
import com.reserve.service.IReserveService;
import com.reserve.service.IUserService;
import com.reserve.vo.InstrumentVo;
import com.reserve.vo.ReserveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("iReserveService")
public class ReserveServiceImpl implements IReserveService {

    @Autowired
    private ReserveInfoMapper reserveInfoMapper;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IInstrumentService iInstrumentService;

    @Override
    public ServerResponse<PageInfo> list(User user, int pageNum, int pageSize) {
        // 利用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<ReserveInfo> reserveInfoList = Lists.newArrayList();
        // 若用户是超级管理员，返回所有用户的预约信息
        // 若用户是普通管理员，返回其管理的实验室的成员的所有预约信息
        // 若用户是普通用户，返回自己的所有预约信息
        if (iUserService.isSuperAdmin(user)) {
            reserveInfoList = reserveInfoMapper.selectByLabId(null);
        } else if (iUserService.isAdmin(user)) {
            reserveInfoList = reserveInfoMapper.selectByLabId(user.getLabId());
        } else {
            reserveInfoList = reserveInfoMapper.selectByUserId(user.getId());
        }
        PageInfo pageResult = new PageInfo(reserveInfoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    @Transactional
    public ServerResponse add(Integer userId, ReserveVo reserveVo) {
        // 根据userId和isntId，将reserveVo中的其他信息补充完善
        User user = iUserService.queryBaseInfoById(userId);
        reserveVo.setUserId(userId);
        reserveVo.setUserRealname(user.getRealname());
        InstrumentVo instrumentVo = iInstrumentService.detail(reserveVo.getInstId()).getData();
        reserveVo.setInstName(instrumentVo.getName());

        // 将前台传来的reserveVo，转化为可以更新到数据库的reserveInfo
        ReserveInfo curReserveInfo = assembleReserveInfo(reserveVo);
        if (!curReserveInfo.getTestStartTime().before(curReserveInfo.getTestEndTime())) {
            return ServerResponse.createByErrorMessage("起始时间应早于结束时间");
        }
        // 判断当前时间段是否已被预约
        List<ReserveVo> reserveVoList = instrumentVo.getUncompletedReserveList();
        for (ReserveVo reserveVoItem : reserveVoList) {
            if (!(curReserveInfo.getTestStartTime().compareTo(reserveVoItem.getTestEndTime()) >= 0
                    || curReserveInfo.getTestEndTime().compareTo(reserveVoItem.getTestStartTime()) <= 0)) {
                return ServerResponse.createByErrorMessage("当前时间段已被预约，请重新选择时间");
            }
        }
        // 设置当前预约有效，当前预约未完成
        curReserveInfo.setEnableStatus(1);
        curReserveInfo.setIsCompleted((byte)0);
        int resultCount = reserveInfoMapper.insert(curReserveInfo);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }

    @Override
    @Transactional
    public ServerResponse update(Integer userId, ReserveVo reserveVo) {
        // 将前台传来的reserveVo，转化为可以更新到数据库的reserveInfo
        ReserveInfo curReserveInfo = assembleReserveInfo(reserveVo);
        // 判断当前时间段是否已被预约
        InstrumentVo instrumentVo = iInstrumentService.detail(curReserveInfo.getInstId()).getData();
        List<ReserveVo> reserveVoList = instrumentVo.getUncompletedReserveList();
        if (!curReserveInfo.getTestStartTime().before(curReserveInfo.getTestEndTime())) {
            return ServerResponse.createByErrorMessage("起始时间应早于结束时间");
        }
        // 判断当前时间段是否已被预约，若数据库中的预约请求id与当前待更新的id相同，则跳过不更新
        for (ReserveVo reserveVoItem : reserveVoList) {
            if (curReserveInfo.getId().intValue()  != reserveVoItem.getId().intValue()
                    && !(curReserveInfo.getTestStartTime().compareTo(reserveVoItem.getTestEndTime()) >= 0
                    || curReserveInfo.getTestEndTime().compareTo(reserveVoItem.getTestStartTime()) <= 0)) {
                return ServerResponse.createByErrorMessage("当前时间段已被预约，请重新选择时间");
            }
        }
        int resultCount = reserveInfoMapper.updateByPrimaryKeySelective(curReserveInfo);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse delete(Integer userId, Integer reserveId) {
        int resultCount = reserveInfoMapper.deleteByPrimaryKeyAndUserId(userId, reserveId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse complete(Integer userId, Integer reserveId) {
        int resultCount = reserveInfoMapper.setCompleteByIdUserId(userId, reserveId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加完成失败");
        }
        return ServerResponse.createBySuccessMessage("添加完成成功");
    }

    @Override
    public ServerResponse<ReserveVo> detail(Integer userId, Integer reserveId) {
        ReserveInfo reserveInfo = reserveInfoMapper.selectByPrimaryKeyAndUserId(userId, reserveId);
        if (reserveInfo == null) {
            return ServerResponse.createByErrorMessage("获取预约信息详情失败");
        }
        ReserveVo reserveVo = assembleReserveVo(reserveInfo);
        return ServerResponse.createBySuccess(reserveVo);
    }

    @Override
    // 返回当前该仪器对应的所有预约信息
    public List<ReserveVo> queryReserveListByInstId(int instId) {
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectByInstId(instId);
        List<ReserveVo> reserveVoList = assembleReserveInfoList(reserveInfoList);
        return reserveVoList;
    }

    @Override
    // 返回当前该仪器的所有未完成的预约信息
    public List<ReserveVo> queryUncompletedReserveListByInstId(int instId) {
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectUncompletedByInstId(instId);
        List<ReserveVo> uncompletedReserveList = assembleReserveInfoList(reserveInfoList);
        return uncompletedReserveList;
    }

    @Override
    // 返回当前该用户的所有预约信息
    public List<ReserveVo> queryReserveListByUserId(int userId) {
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectByUserId(userId);
        List<ReserveVo> reserveVoList = assembleReserveInfoList(reserveInfoList);
        return reserveVoList;
    }

    @Override
    // 返回当前该用户的所有未完成的预约信息
    public List<ReserveVo> queryUncompletedReserveListByUserId(int userId) {
        List<ReserveInfo> reserveInfoList = reserveInfoMapper.selectUncompletedByUserId(userId);
        List<ReserveVo> uncompletedReserveList = assembleReserveInfoList(reserveInfoList);
        return uncompletedReserveList;
    }

    private List<ReserveVo> assembleReserveInfoList(List<ReserveInfo> reserveInfoList) {
        List<ReserveVo> reserveVoList = Lists.newArrayList();
        for (ReserveInfo reserveInfo : reserveInfoList) {
            ReserveVo reserveVo = assembleReserveVo(reserveInfo);
            reserveVoList.add(reserveVo);
        }
        return reserveVoList;
    }

    private ReserveVo assembleReserveVo(ReserveInfo reserveInfo) {
        ReserveVo reserveVo = new ReserveVo();
        reserveVo.setId(reserveInfo.getId());
        reserveVo.setUserId(reserveInfo.getUserId());
        reserveVo.setUserRealname(reserveInfo.getUserRealname());
        reserveVo.setInstId(reserveInfo.getInstId());
        reserveVo.setInstName(reserveInfo.getInstName());
        reserveVo.setEnableStatus(reserveInfo.getEnableStatus());
        Date testStartTime = reserveInfo.getTestStartTime();
        Date testEndTime = reserveInfo.getTestEndTime();
        reserveVo.setTestStartTime(testStartTime);
        reserveVo.setTestEndTime(testEndTime);
        reserveVo.setTestStartYear(testStartTime.getYear() + 1900);
        reserveVo.setTestStartMonth(testStartTime.getMonth() + 1);
        reserveVo.setTestStartDay(testStartTime.getDate());
        reserveVo.setTestStartHour(testStartTime.getHours());
        reserveVo.setTestStartMinute(testStartTime.getMinutes());
        reserveVo.setTestEndYear(testEndTime.getYear() + 1900);
        reserveVo.setTestEndMonth(testEndTime.getMonth() + 1);
        reserveVo.setTestEndDay(testEndTime.getDate());
        reserveVo.setTestEndHour(testEndTime.getHours());
        reserveVo.setTestEndMinute(testEndTime.getMinutes());
        return reserveVo;
    }

    private ReserveInfo assembleReserveInfo(ReserveVo reserveVo) {
        ReserveInfo reserveInfo = new ReserveInfo();
        reserveInfo.setId(reserveVo.getId());
        reserveInfo.setUserId(reserveVo.getUserId());
        reserveInfo.setUserRealname(reserveVo.getUserRealname());
        reserveInfo.setInstId(reserveVo.getInstId());
        reserveInfo.setInstName(reserveVo.getInstName());
        Date testStartTime = new Date(reserveVo.getTestStartYear() - 1900, reserveVo.getTestStartMonth() - 1,
                reserveVo.getTestStartDay(), reserveVo.getTestStartHour(), reserveVo.getTestStartMinute());
        Date testEndTime = new Date(reserveVo.getTestEndYear() - 1900, reserveVo.getTestEndMonth() - 1,
                reserveVo.getTestEndDay(), reserveVo.getTestEndHour(), reserveVo.getTestEndMinute());
        reserveInfo.setTestStartTime(testStartTime);
        reserveInfo.setTestEndTime(testEndTime);
        return reserveInfo;
    }
}

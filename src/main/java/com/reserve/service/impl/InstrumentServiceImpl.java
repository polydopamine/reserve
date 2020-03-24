package com.reserve.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.reserve.common.ServerResponse;
import com.reserve.dao.InstrumentMapper;
import com.reserve.pojo.Instrument;
import com.reserve.pojo.Lab;
import com.reserve.pojo.User;
import com.reserve.service.IInstrumentService;
import com.reserve.service.ILabService;
import com.reserve.service.IReserveService;
import com.reserve.service.IUserService;
import com.reserve.vo.InstrumentVo;
import com.reserve.vo.ReserveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iInstrumentService")
public class InstrumentServiceImpl implements IInstrumentService {

    @Autowired
    private InstrumentMapper instrumentMapper;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IReserveService iReserveService;
    @Autowired
    private ILabService iLabService;

    @Override
    public ServerResponse<PageInfo> list(User user, int pageNum, int pageSize) {
        // 利用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Instrument> instrumentList = Lists.newArrayList();
//        if (iUserService.isSuperAdmin(user)) {
//            // 若是超级管理员，返回所有用户的信息
//            instrumentList = instrumentMapper.selectInstrumentByLabId(null);
//        } else {
//            // 若是普通管理员，返回与其相同课题组的用户信息
//            instrumentList = instrumentMapper.selectInstrumentByLabId(user.getLabId());
//        }
        instrumentList = instrumentMapper.selectInstrumentByLabId(user.getLabId());
        PageInfo pageResult = new PageInfo(instrumentList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse add(Instrument instrument) {
        instrument.setEnableStatus(1);
        int resultCount = instrumentMapper.insert(instrument);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }

    @Override
    public ServerResponse update(Instrument instrument) {
        int resultCount = instrumentMapper.updateByPrimaryKeySelective(instrument);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse delete(Integer instrumentId) {
        int resultCount = instrumentMapper.deleteByPrimaryKey(instrumentId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public Instrument queryById(int instId) {
        Instrument instrument = instrumentMapper.selectByPrimaryKey(instId);
        return instrument;
    }

    @Override
    public ServerResponse<InstrumentVo> detail(Integer instId) {
        Instrument instrument = instrumentMapper.selectByPrimaryKey(instId);
        if (instrument == null) {
            return ServerResponse.createByErrorMessage("获取失败");
        }
        InstrumentVo instrumentVo = assembleInstrumentVo(instrument);
        List<ReserveVo> reserveList = iReserveService.queryReserveListByInstId(instId);
        List<ReserveVo> uncompletedReserveList = iReserveService.queryUncompletedReserveListByInstId(instId);
        instrumentVo.setReserveList(reserveList);
        instrumentVo.setUncompletedReserveList(uncompletedReserveList);
        return ServerResponse.createBySuccess(instrumentVo);
    }

    private InstrumentVo assembleInstrumentVo(Instrument instrument){
        InstrumentVo instrumentVo = new InstrumentVo();
        instrumentVo.setId(instrument.getId());
        instrumentVo.setName(instrument.getName());
        instrumentVo.setModel(instrument.getModel());
        instrumentVo.setLocation(instrument.getLocation());
        instrumentVo.setImage(instrument.getImage());
        Lab lab = iLabService.queryByLabId(instrument.getLabId());
        instrumentVo.setLabId(lab.getId());
        instrumentVo.setLabName(lab.getName());
        instrumentVo.setEnableStatus(instrument.getEnableStatus());
        instrumentVo.setDescription(instrument.getDescription());
        return instrumentVo;
    }
}

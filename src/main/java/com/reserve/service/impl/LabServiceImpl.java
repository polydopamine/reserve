package com.reserve.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.dao.LabMapper;
import com.reserve.pojo.Lab;
import com.reserve.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iLabService")
public class LabServiceImpl implements ILabService {

    @Autowired
    private LabMapper labMapper;

    @Override
    // 返回课题组列表
    public ServerResponse<PageInfo> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lab>  labList = labMapper.listAll();
        PageInfo pageResult = new PageInfo(labList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse add(Lab lab) {
        lab.setDepartId(1);
        lab.setDepartName("高分子系");
        int resultCount = labMapper.insert(lab);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }

    @Override
    public ServerResponse update(Lab lab) {
        int resultCount = labMapper.updateByPrimaryKeySelective(lab);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse delete(Integer labId) {
        int resultCount = labMapper.deleteByPrimaryKey(labId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public Lab queryByLabId(Integer labId) {
        Lab lab = labMapper.selectByPrimaryKey(labId);
        return lab;
    }
}

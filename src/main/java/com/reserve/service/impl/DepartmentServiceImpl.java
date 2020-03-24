package com.reserve.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.dao.DepartmentMapper;
import com.reserve.pojo.Department;
import com.reserve.pojo.Lab;
import com.reserve.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iDepartmentService")
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public ServerResponse<PageInfo> list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> departmentList = departmentMapper.listAll();
        PageInfo pageResult = new PageInfo(departmentList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse add(Department department) {
        int resultCount = departmentMapper.insert(department);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse update(Department department) {
        int resultCount = departmentMapper.updateByPrimaryKeySelective(department);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse delete(Integer departId) {
        int resultCount = departmentMapper.deleteByPrimaryKey(departId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }
}

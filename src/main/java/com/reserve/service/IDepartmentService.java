package com.reserve.service;

import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.Department;
import com.reserve.pojo.Lab;

public interface IDepartmentService {
    // 返回课题组列表
    ServerResponse<PageInfo> list(int pageNum, int pageSize);

    // 新增课题组的信息
    ServerResponse add(Department department);

    // 对课题组的信息进行修改
    ServerResponse update(Department department);

    // 删除课题组的信息
    ServerResponse delete(Integer departId);
}

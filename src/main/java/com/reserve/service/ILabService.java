package com.reserve.service;

import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.Lab;

public interface ILabService {

    ServerResponse<PageInfo> list(int pageNum, int pageSize);

    ServerResponse add(Lab lab);

    ServerResponse update(Lab lab);

    ServerResponse delete(Integer labId);

    Lab queryByLabId(Integer labId);

}

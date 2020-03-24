package com.reserve.service;

import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.Instrument;
import com.reserve.pojo.User;
import com.reserve.vo.InstrumentVo;

public interface IInstrumentService {

    ServerResponse<PageInfo> list(User user, int pageNum, int pageSize);
    ServerResponse add(Instrument instrument);
    ServerResponse update(Instrument instrument);
    ServerResponse delete(Integer instrumentId);
    ServerResponse<InstrumentVo> detail(Integer instId);
    Instrument queryById(int instId);
}

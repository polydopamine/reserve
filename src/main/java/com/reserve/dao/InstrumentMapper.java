package com.reserve.dao;

import com.reserve.pojo.Instrument;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstrumentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Instrument record);

    int insertSelective(Instrument record);

    Instrument selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Instrument record);

    int updateByPrimaryKeyWithBLOBs(Instrument record);

    int updateByPrimaryKey(Instrument record);

    List<Instrument> selectInstrumentByLabId(@Param("labId") Integer labId);
}
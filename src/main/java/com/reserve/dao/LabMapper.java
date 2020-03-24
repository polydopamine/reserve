package com.reserve.dao;

import com.reserve.pojo.Lab;

import java.util.List;

public interface LabMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lab record);

    int insertSelective(Lab record);

    Lab selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Lab record);

    int updateByPrimaryKey(Lab record);

    List<Lab> listAll();

}
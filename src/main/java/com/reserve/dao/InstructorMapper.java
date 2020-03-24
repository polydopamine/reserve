package com.reserve.dao;

import com.reserve.pojo.Instructor;

public interface InstructorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Instructor record);

    int insertSelective(Instructor record);

    Instructor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Instructor record);

    int updateByPrimaryKey(Instructor record);
}
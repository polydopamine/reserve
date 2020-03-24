package com.reserve.dao;

import com.reserve.pojo.ReserveInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReserveInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReserveInfo record);

    int insertSelective(ReserveInfo record);

    ReserveInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReserveInfo record);

    int updateByPrimaryKey(ReserveInfo record);

    List<ReserveInfo> selectByLabId(Integer labId);

    List<ReserveInfo> selectByUserId(Integer userId);

    List<ReserveInfo> selectUncompletedByUserId(Integer userId);

    List<ReserveInfo> selectByInstId(Integer instId);

    List<ReserveInfo> selectUncompletedByInstId(Integer instId);

    int deleteByPrimaryKeyAndUserId(@Param("userId") Integer userId, @Param("reserveId") Integer reserveId);

    ReserveInfo selectByPrimaryKeyAndUserId(@Param("userId") Integer userId, @Param("reserveId") Integer reserveId);

    int setCompleteByIdUserId(@Param("userId") Integer userId, @Param("reserveId") Integer reserveId);

}
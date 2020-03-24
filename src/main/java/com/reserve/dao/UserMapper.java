package com.reserve.dao;

import com.reserve.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectUsername(String username);

    int selectEmail(String email);

    User selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    List<User> selectUserByLabId(Integer labId);

    int checkUserIdPassword(@Param("userId") Integer userId, @Param("password") String password);

    int updatePasswordByUserId(@Param("userId") Integer userId, @Param("password") String password);

    List<User> listInstructor();

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);
}
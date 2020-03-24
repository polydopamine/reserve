package com.reserve.service;

import com.github.pagehelper.PageInfo;
import com.reserve.common.ServerResponse;
import com.reserve.pojo.User;
import com.reserve.vo.UserVo;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<PageInfo> list(User user, int pageNum, int pageSize);

    ServerResponse adminUpdate(User user);

    boolean isSuperAdmin(User user);

    boolean isAdmin(User user);

    ServerResponse register(User user);

    ServerResponse checkValid(String type, String str);

    ServerResponse update(User user, User updateUser);

    ServerResponse resetPassword(String username, String passwordOld, String passwordNew);

    ServerResponse<PageInfo> listInstructor(int pageNum, int pageSize);

    User queryBaseInfoById(Integer id);

    ServerResponse<UserVo> detail(Integer userId);

    ServerResponse add(User user);
}

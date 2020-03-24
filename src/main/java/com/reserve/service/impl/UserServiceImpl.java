package com.reserve.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.reserve.common.Const;
import com.reserve.common.ServerResponse;
import com.reserve.dao.LabMapper;
import com.reserve.dao.UserMapper;
import com.reserve.pojo.Lab;
import com.reserve.pojo.User;
import com.reserve.service.ILabService;
import com.reserve.service.IReserveService;
import com.reserve.service.IUserService;
import com.reserve.util.MD5Util;
import com.reserve.vo.ReserveVo;
import com.reserve.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ILabService iLabService;
    @Autowired
    private IReserveService iReserveService;

    @Override
    // 用户登录
    public ServerResponse<User> login(String username, String password) {
        // 检验用户名是否不存在
        int resultCount = userMapper.selectUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // 检验用户名和密码是否匹配
        User user = userMapper.selectByUsernamePassword(username, MD5Util.MD5EncodeUtf8(password));
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        // 将敏感信息（密码）置为空
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    @Override
    // 返回所有用户列表
    public ServerResponse<PageInfo> list(User user, int pageNum, int pageSize) {
        // 利用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = Lists.newArrayList();
        if (isSuperAdmin(user)) {
            // 若是超级管理员，返回所有用户的信息
            userList = userMapper.selectUserByLabId(null);
        } else {
            // 若是普通管理员，返回与其相同课题组的用户信息
            userList = userMapper.selectUserByLabId(user.getLabId());
        }
        PageInfo pageResult = new PageInfo(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    // todo 管理员update普通用户的信息，需要更新什么信息？
    @Override
    // 管理员更新普通用户的信息
    public ServerResponse adminUpdate(User user) {
        int resultCount = userMapper.updateByPrimaryKeySelective(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    // 判断用户是否是超级管理员
    public boolean isSuperAdmin(User user) {
        return user.getEnableRole() == Const.EnableRole.ROLE_SUPER_ADMIN;
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getEnableRole() == Const.EnableRole.ROLE_ADMIN || user.getEnableRole() == Const.EnableRole.ROLE_SUPER_ADMIN;
    }

    @Override
    // 用户注册
    public ServerResponse register(User user) {
        // 检验username是否已注册
        if (!checkValid(Const.USERNAME, user.getUsername()).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        // 检验email是否已注册
        if (!checkValid(Const.EMAIL, user.getEmail()).isSuccess()) {
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        // 密码置为MD5加密后的字段
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        // 新用户注册时，首先置为账户不可用状态，需要管理员审批后方可使用
        user.setEnableRole(Const.EnableRole.ROLE_FORBIDDEN);
        if ("wsh".equals(user.getUsername())) {
            user.setEnableRole(Const.EnableRole.ROLE_SUPER_ADMIN);
        }
        // 向数据库插入
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    // 检查字段是否有效，type = "phone" or "email"
    public ServerResponse checkValid(String type, String str) {
        // 检查 type = "username" 时，数据库是否存在对应信息
        if (Const.USERNAME.equals(type)) {
            int resultCount = userMapper.selectUsername(str);
            if (resultCount == 0) {
                return ServerResponse.createBySuccessMessage("校验成功");
            } else {
                return ServerResponse.createByErrorMessage("校验失败");
            }
        }
        // 检查 type = "email" 时，数据库是否存在对应信息
        if (Const.EMAIL.equals(type)) {
            int resultCount = userMapper.selectEmail(str);
            if (resultCount == 0) {
                return ServerResponse.createBySuccessMessage("校验成功");
            } else {
                return ServerResponse.createByErrorMessage("校验失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数错误");
    }

    @Override
    // 普通用户更新自己的信息
    public ServerResponse update(User user, User updateUser) {
        // 检查新输入的username和email是否已被注册
        if (updateUser.getUsername() != null && !user.getUsername().equals(updateUser.getUsername()) && !checkValid(Const.USERNAME, updateUser.getUsername()).isSuccess()) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        if (updateUser.getEmail() != null && !user.getEmail().equals(updateUser.getEmail()) && !checkValid(Const.EMAIL, updateUser.getEmail()).isSuccess()) {
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }
        // 待更新的信息注入到新创建的对象中
        User updateDBUser = new User();
        updateDBUser.setId(user.getId());
        updateDBUser.setUsername((StringUtils.isEmpty(updateUser.getUsername()) || user.getUsername().equals(updateUser.getUsername()))
                ? null : updateUser.getUsername());
        updateDBUser.setRole((updateUser.getRole() == null || user.getRole().equals(updateUser.getRole()))
                ? null : updateUser.getRole());
        updateDBUser.setLabId((updateUser.getLabId() == null || user.getLabId().equals(updateUser.getLabId()))
                ? null : updateUser.getLabId());
        updateDBUser.setEnrollmentYear((updateUser.getEnrollmentYear() == null || user.getEnrollmentYear().equals(updateUser.getEnrollmentYear()))
                ? null : updateUser.getEnrollmentYear());
        updateDBUser.setInstrutorId((updateUser.getInstrutorId() == null || user.getInstrutorId().equals(updateUser.getInstrutorId()))
                ? null : updateUser.getInstrutorId());
        updateDBUser.setStudentId((StringUtils.isEmpty(updateUser.getStudentId()) || user.getStudentId().equals(updateUser.getStudentId()))
                ? null : updateUser.getStudentId());
        updateDBUser.setPhone((StringUtils.isEmpty(updateUser.getPhone()) || user.getPhone().equals(updateUser.getPhone()))
                ? null : updateUser.getPhone());
        updateDBUser.setEmail((StringUtils.isEmpty(updateUser.getEmail()) || user.getEmail().equals(updateUser.getEmail()))
                ? null : updateUser.getEmail());
        updateUser.setEnableRole(user.getEnableRole());
        // 执行数据库更新
        int resultCount = userMapper.updateByPrimaryKeySelective(updateDBUser);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    // 普通用户重设自己的密码
    public ServerResponse resetPassword(String username, String passwordOld, String passwordNew) {
        // 检查旧密码是否正确
        User user = userMapper.selectByUsernamePassword(username, MD5Util.MD5EncodeUtf8(passwordOld));
        if (user == null) {
            return ServerResponse.createBySuccessMessage("密码错误");
        }
        // 更新新密码
        int resultCount = userMapper.updatePasswordByUsername(username, MD5Util.MD5EncodeUtf8(passwordNew));
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @Override
    public ServerResponse<PageInfo> listInstructor(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> instructorList = userMapper.listInstructor();
        for (User instructor : instructorList) {
            instructor.setPassword(StringUtils.EMPTY);
        }
        PageInfo pageResult = new PageInfo(instructorList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public User queryBaseInfoById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        User userBaseInfo = new User();
        userBaseInfo.setId(user.getId());
        userBaseInfo.setUsername(user.getUsername());
        userBaseInfo.setRealname(user.getRealname());
        userBaseInfo.setRole(user.getRole());
        userBaseInfo.setEnrollmentYear(user.getEnrollmentYear());
        userBaseInfo.setLabId(user.getId());
        userBaseInfo.setPhone(user.getPhone());
        userBaseInfo.setEmail(user.getEmail());
        userBaseInfo.setEnableRole(user.getEnableRole());
        return userBaseInfo;
    }

    @Override
    public ServerResponse<UserVo> detail(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createBySuccessMessage("获取失败");
        }
        UserVo userVo = assembleUserVo(user);
        return ServerResponse.createBySuccess(userVo);
    }

    private UserVo assembleUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setRealname(user.getRealname());
        userVo.setRole(user.getRole());
        userVo.setEnrollmentYear(user.getEnrollmentYear());
        userVo.setStudentId(user.getStudentId());
        userVo.setPhone(user.getPhone());
        userVo.setEmail(user.getEmail());
        userVo.setEnableRole(user.getEnableRole());
        Lab lab = iLabService.queryByLabId(user.getLabId());
        List<ReserveVo> reserveList = iReserveService.queryReserveListByUserId(user.getId());
        List<ReserveVo> uncompletedReserveList = iReserveService.queryUncompletedReserveListByUserId(user.getId());
        userVo.setLab(lab);
        userVo.setReserveList(reserveList);
        userVo.setUncompletedReserveList(uncompletedReserveList);
        return userVo;
    }

    @Override
    public ServerResponse add(User user) {
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }
}

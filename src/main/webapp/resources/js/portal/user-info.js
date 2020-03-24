// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var userInfoUrl = '/user/get_user_info.do';
var labListUrl = '/admin/lab/list.do';


var page = {
    init : function(){
        formError.hide();
        this.initData();
        this.bindEvent();
    },
    initData : function(){
        $.getJSON(userInfoUrl, function(data){
            if (!data.status){
                $('#username').text(data.data.username);
                $('#realname').text(data.data.realname);
                $('#phone').text(data.data.phone);
                $('#email').text(data.data.email);
                $('#student-id').text(data.data.studentId);
                $('#role').text(_common.getRoleText(data.data.role));
                $('#year').text(data.data.enrollmentYear);
                $('#lab').text(data.data.lab.name);
            }
        });
    },
    bindEvent : function(){
        var _this = this;
        $('#username').blur(function(){
            var username = $.trim($('#username').val());
            if (!username){
                return;
            }
            _user.checkUsername(username, function(){
                formError.hide();
            }, function(errMsg){
                formError.show("用户名已存在");
            })
        });
        $('#submit').click(function(){
            _this.submit();
        });
    },
    submit : function(){
        var formData = {
            username        : $.trim($('#username').val()),
            realname        : $.trim($('#realname').val()),
            password        : $.trim($('#password').val()),
            passwordConfirm : $.trim($('#password-confirm').val()),
            phone           : $.trim($('#phone').val()),
            email           : $.trim($('#email').val()),
            studentId       : $.trim($('#student-id').val()),
            role            : $.trim($('#role').val()),
            enrollmentYear  : $.trim($('#year').val()),
            labId           : $.trim($('#lab').val())
        };
        var validateResult = this.validate(formData);
        if (validateResult.status) {
            _user.register(formData, function(){
                window.location.href = './door/user/login.do';
            }, function(errMsg){
                formError.show(errMsg);
            });
        } else {
            formError.show(validateResult.msg);
        }
    },
    validate : function(formData){
        var validateResult = {
            status : false,
            msg    : ''
        };
        if (!_common.validate(formData.username, 'require')){
            validateResult.msg = '用户名不能为空';
            return validateResult;
        }
        if (!_common.validate(formData.realname, 'require')){
            validateResult.msg = '真实姓名不能为空';
            return validateResult;
        }
        if (!_common.validate(formData.phone, 'phone')){
            validateResult.msg = '手机号格式不正确';
            return validateResult;
        }
        if (!_common.validate(formData.email, 'email')){
            validateResult.msg = '邮箱格式不正确';
            return validateResult;
        }
        if (!_common.validate(formData.studentId, 'require')){
            validateResult.msg = '学生号/工号不能为空';
            return validateResult;
        }
        if (!_common.validate(formData.role, 'require')){
            validateResult.msg = '用户身份不能为空';
            return validateResult;
        }
        if (!_common.validate(formData.enrollmentYear, 'require')){
            validateResult.msg = '入学年份不能为空';
            return validateResult;
        }
        if (!_common.validate(formData.labId, 'require')){
            validateResult.msg = '课题组不能为空';
            return validateResult;
        }
        validateResult.status = true;
        validateResult.msg = '验证成功';
        return validateResult;
    }
};

$(function(){
    page.init();
});
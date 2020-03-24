// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var page = {
    init : function(){
        formError.hide();
        this.bindEvent();
    },
    // 绑定事件
    bindEvent : function(){
        var _this = this;
        // 点击提交按钮，验证表单内容，提交到后台
        $('#submit').click(function(){
            _this.submit();
        });
    },
    submit : function(){
        var formData = {
            username        : $.trim($('#username').val()),
            // realname        : $.trim($('#realname').val()),
            passwordOld     : $.trim($('#password-old').val()),
            passwordNew     : $.trim($('#password-new').val()),
            passwordConfirm : $.trim($('#password-confirm').val())
        };
        var validateResult = this.validate(formData);
        if (validateResult.status) {
            _user.resetPassword(formData, function(){
                // 重置密码成功，跳转到登录页面
                alert('修改成功');
                window.location.href = '/user/login';
            }, function(errMsg){
                formError.show(errMsg);
            });
        } else {
            formError.show(validateResult.msg);
        }
    },
    // 验证表单内容
    validate : function(formData){
        var validateResult = {
            status : false,
            msg    : ''
        };
        if (!_common.validate(formData.username, 'require')){
            validateResult.msg = '用户名不能为空';
            return validateResult;
        }
        // if (!_common.validate(formData.realname, 'require')){
        //     validateResult.msg = '真实姓名不能为空';
        //     return validateResult;
        // }
        if (!_common.validate(formData.passwordNew, 'require') || formData.passwordNew.length < 5){
            validateResult.msg = '请输入不少于5位的密码';
            return validateResult;
        }
        if (formData.passwordNew !== formData.passwordConfirm){
            validateResult.msg = '两次输入的密码不一致';
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
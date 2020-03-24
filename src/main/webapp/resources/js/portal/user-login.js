// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

// page逻辑部分
var page = {
    init : function(){
        formError.hide();
        this.bindEvent();
    },
    bindEvent : function(){
        var _this = this;
        // 登录按钮的点击
        $('#submit').click(function(){
            _this.submit();
        });
        // 如果按下回车页，也进行提交
        $('.user-content').keyup(function(e){
            // keyCode === 13 表示回车键
            if (e.keyCode === 13) {
                _this.submit();
            }
        });
    },
    // 提交表单
    submit : function(){
        var formData = {
            username : $.trim($('#username').val()),
            password : $.trim($('#password').val())
        };
        var validateResult = this.formValidate(formData);
        // 验证成功
        if (validateResult.status) {
            _user.login(formData, function(res){
                // 若验证成功，跳转到来此页面之前的页面，或首页
                window.location.href = '/reserve/list';
            }, function(errMsg){
                formError.show(errMsg);
            });
        }
        // 验证失败
        else {
            // 错误提示
            formError.show(validateResult.msg);
        }
    },
    // 表单字段的验证
    formValidate : function(formData){
        var result = {
            status  : false,
            msg     : ''
        };
        if (!_common.validate(formData.username, 'require')){
            result.msg = '用户名不能为空';
            return result;
        }
        if (!_common.validate(formData.password, 'require')){
            result.msg = '密码不能为空';
            return result;
        }
        // 通过验证，返回正确提示
        result.status   = true;
        result.msg      = '验证通过';
        return result;
    }
};

$(function(){
    page.init();
});
// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var labListUrl = '/admin/lab/list.do';

var page = {
    init : function(){
        formError.hide();
        // 从后台取数据，获取可供选择的课题组列表
        this.initLabInfo();
        // 初始化可供选择的入学年份
        this.initDate();
        // 绑定事件
        this.bindEvent();
    },
    initLabInfo : function(){
        $.getJSON(labListUrl, function(data){
            if (!data.status){
                // 返回的数据为PageHelper格式
                var labList = data.data.list;
                var optionHtml = '';
                labList.map(function(item, index){
                    optionHtml += '<option value="' + item.id + '" id="' + item.id + '">' + item.name + '</option>';
                });
                $('#lab').html(optionHtml);
            }
        });
    },
    initDate : function(){
        var optionHtml = '';
        var year = new Date().getFullYear();
        for (var i = year - 30; i <= year + 2; i++) {
            optionHtml += '<option value="' + i + '" id="' + i + '">' + i + '</option>';
        }
        $('#year').html(optionHtml);
    },
    bindEvent : function(){
        var _this = this;
        // 在username框内输入完成后，立即传给后台检查：用户名是否重复
        $('#username').blur(function(){
            // 用户名为空时，不进行检查
            var username = $.trim($('#username').val());
            if (!username){
                return;
            }
            // 检查若成功，隐藏错误框；检查不通过，在错误框内显示错误信息
            _user.checkUsername(username, function(){
                formError.hide();
            }, function(errMsg){
                formError.show("用户名已存在");
            })
        });
        // 点击submit按钮，向后台提交数据
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
        // 对输入的数据进行初步检查，是否符合规范
        var validateResult = this.validate(formData);
        if (validateResult.status) {
            _user.register(formData, function(){
                // 若注册成功，跳转到登录页面
                alert("注册成功");
                window.location.href = '/user/login';
            }, function(errMsg){
                formError.show(errMsg);
            });
        } else {
            formError.show(validateResult.msg);
        }
    },
    // 对各项数据是否为空进行检查
    // 检查密码不少于6位，两次输入的密码一致
    // 电话和邮箱的格式符合规范
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
        if (!_common.validate(formData.password, 'require') || formData.password.length < 5){
            validateResult.msg = '请输入不少于5位的密码';
            return validateResult;
        }
        if (formData.password !== formData.passwordConfirm){
            validateResult.msg = '两次输入的密码不一致';
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
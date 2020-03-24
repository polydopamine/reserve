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
var originalUserInfo;

var page = {
    init : function(){
        formError.hide();
        // 初始化课题组选择列表
        this.initLabInfo();
        // 初始化入学年份列表
        this.initDate();
        // 利用后台取出的数据，初始化其他option选项
        this.initData();
        // 绑定触发事件
        this.bindEvent();
    },
    initLabInfo : function(){
        $.getJSON(labListUrl, function(data){
            if (!data.status){
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
    initData : function(){
        $.getJSON(userInfoUrl, function(data){
            if (!data.status){
                originalUserInfo = data.data;
                $('#username').val(data.data.username);
                $('#realname').val(data.data.realname);
                $('#phone').val(data.data.phone);
                $('#email').val(data.data.email);
                $('#student-id').val(data.data.studentId);
                $('#role option[id="' + data.data.role + '"]').attr('selected', 'selected');
                $('#year option[id="' + data.data.enrollmentYear + '"]').attr('selected', 'selected');
                $('#lab option[id="' + data.data.labId + '"]').attr('selected', 'selected');
            }
        });
    },
    bindEvent : function(){
        var _this = this;
        $('#username').blur(function(){
            var username = $.trim($('#username').val());
            if (!username || username === originalUserInfo.username){
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
            phone           : $.trim($('#phone').val()),
            email           : $.trim($('#email').val()),
            studentId       : $.trim($('#student-id').val()),
            role            : $.trim($('#role').val()),
            enrollmentYear  : $.trim($('#year').val()),
            labId           : $.trim($('#lab').val())
        };
        var validateResult = this.validate(formData);
        // if (validateResult.status) {
            _user.updateUserInfo(formData, function(){
                alert('修改成功');
                window.location.href = '/reserve/list';
            }, function(errMsg){
                formError.show(errMsg);
            });
        // } else {
        //     formError.show(validateResult.msg);
        // }
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
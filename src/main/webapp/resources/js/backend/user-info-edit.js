// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var userInfoUrl = '/admin/user/get_user_info.do?userId=';
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
        userInfoUrl += _common.getUrlParam('userId');
        $.getJSON(userInfoUrl, function(data){
            if (!data.status){
                originalUserInfo = data.data;
                $('#username').val(data.data.username);
                $('#realname').val(data.data.realname);
                $('#phone').val(data.data.phone);
                $('#email').val(data.data.email);
                $('#student-id').val(data.data.studentId);
                $('#enable-role').val(data.data.enableRole);
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
            id              : _common.getUrlParam('userId'),
            username        : $.trim($('#username').val()),
            realname        : $.trim($('#realname').val()),
            phone           : $.trim($('#phone').val()),
            email           : $.trim($('#email').val()),
            studentId       : $.trim($('#student-id').val()),
            enableRole      : $.trim($('#enable-role').val()),
            role            : $.trim($('#role').val()),
            enrollmentYear  : $.trim($('#year').val()),
            labId           : $.trim($('#lab').val())
        };
        _user.adminUpdateUserInfo(formData, function(){
            alert('修改成功');
            window.location.href = '/admin/user/list';
        }, function(errMsg){
            formError.show(errMsg);
        });
    }
};

$(function(){
    page.init();
});
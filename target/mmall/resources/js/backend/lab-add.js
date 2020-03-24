$(function(){
    page.init();
});
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
        // 绑定事件
        this.bindEvent();
    },
    bindEvent : function(){
        var _this = this;
        // 点击submit按钮，向后台提交数据
        $('#submit').click(function(){
            _this.submit();
        });
    },
    submit : function(){
        var formData = {
            name        : $.trim($('#name').val()),
        };
        _lab.add(formData, function(){
            // 若注册成功，跳转到登录页面
            alert("添加成功");
            window.location.href = '/admin/lab/list';
        }, function(errMsg){
            formError.show(errMsg);
        });
    }
};

$(function(){
    page.init();
});
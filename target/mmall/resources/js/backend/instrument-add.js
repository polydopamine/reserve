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
            model        : $.trim($('#model').val()),
            location        : $.trim($('#location').val()),
            description : $.trim($('#description').val()),
            image           : $.trim($('#image').val()),
            labId           : $.trim($('#lab').val())
        };
        // 对输入的数据进行初步检查，是否符合规范
        // var validateResult = this.validate(formData);
        // if (validateResult.status) {
        _instrument.add(formData, function(){
            // 若注册成功，跳转到登录页面
            alert("添加成功");
            window.location.href = '/admin/inst/list';
        }, function(errMsg){
            formError.show(errMsg);
        });
        // } else {
        //     formError.show(validateResult.msg);
        // }
    }
};

$(function(){
    page.init();
});
// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var instInfoUrl = '/inst/detail.do?instId=';
var labListUrl = '/admin/lab/list.do';
var originalUserInfo;

var page = {
    init : function(){
        formError.hide();
        // 初始化课题组选择列表
        this.initLabInfo();
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
    initData : function(){
        instInfoUrl += _common.getUrlParam('instId');
        $.getJSON(instInfoUrl, function(data){
            if (!data.status){
                originalUserInfo = data.data;
                $('#name').val(data.data.name);
                $('#model').val(data.data.model);
                $('#location').val(data.data.location);
                $('#image').val(data.data.image);
                $('#description').val(data.data.description);
                $('#enable-status option[id="' + data.data.enableStatus + '"]').attr('selected', 'selected');
                $('#lab option[id="' + data.data.labId + '"]').attr('selected', 'selected');
            }
        });
    },
    bindEvent : function(){
        var _this = this;
        $('#submit').click(function(){
            _this.submit();
        });
    },
    submit : function(){
        var formData = {
            id              : _common.getUrlParam('instId'),
            name            : $.trim($('#name').val()),
            model           : $.trim($('#model').val()),
            location        : $.trim($('#location').val()),
            description     : $.trim($('#description').val()),
            image           : $.trim($('#image').val()),
            enableStatus    : $.trim($('#enable-status').val()),
            labId           : $.trim($('#lab').val())
        };
        _instrument.update(formData, function(){
            alert('修改成功');
            window.location.href = '/admin/inst/list';
        }, function(errMsg){
            formError.show(errMsg);
        });
    }
};

$(function(){
    page.init();
});
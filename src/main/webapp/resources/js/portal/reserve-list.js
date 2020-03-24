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
var listReservationUrl = '/user/detail.do';
var userVo;

var page = {
    init : function(){
        formError.hide();
        this.intiUserInfo();
        this.initData();
        this.bindEvent();
    },
    intiUserInfo : function(){
        $.getJSON(userInfoUrl, function(data){
            $('#realname').text(data.data.realname);
            $('#edit-user-info').val('<a href="/user/edit?userId=' + data.data.id + '" class="link" target="_blank">修改个人信息</a>');
            if (data.data.enableRole !== 0) {
                $('.admin-item').hide();
            }
        });
    },
    initData : function(){
        $.getJSON(listReservationUrl, function(data){
            if (!data.status){
                userVo = data.data;
                var templateHtml = '';
                var reservationList = userVo.uncompletedReserveList;
                reservationList.map(function(item, index){
                    var startTimeStr = item.testStartYear + '.' + item.testStartMonth + '.' + item.testStartDay + '  ' +
                        _date.toTwoDigit(item.testStartHour) + item.testStartHour + ':' + _date.toTwoDigit(item.testStartMinute) + item.testStartMinute;
                    var endTimeStr = item.testEndYear + '.' + item.testEndMonth + '.' + item.testEndDay + '  ' +
                        _date.toTwoDigit(item.testEndHour) + item.testEndHour + ':' + _date.toTwoDigit(item.testEndMinute) + item.testEndMinute;
                    templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                        '<td>' + (index + 1) + '</td><td>' + item.instName +
                        '</td><td>' + startTimeStr + '</td><td>' + endTimeStr +
                        '</td><td class="edit"><a href="/reserve/edit?reserveId=' + item.id +
                        '">编辑</a></td><td><a href="/reserve/delete.do?reserveId=' + item.id +
                        '">删除</a></td></td><td><a href="/reserve/complete.do?reserveId=\' + item.id + \'">已完成</a></td></tr>';
                });
                $('tbody').append(templateHtml);
                $('#test-uncompleted-times').text(userVo.uncompletedReserveList.length);
                $('#test-total-times').text(userVo.reserveList.length);
            }
        });
    },
    bindEvent : function(){
        var _this = this;
        $('.delete').click(function(){
            var inservationId = $(this).parent().find("tr").val();
            _this.delete(inservationId);
        });
    },
    delete : function(inservationId){
        var _this = this;
        _reserve.delete(inservationId, function(){
            alert('删除成功');
            _this.initData();
        }, function(){
            alert('删除失败');
        });
    }
};

$(function(){
    page.init();
});
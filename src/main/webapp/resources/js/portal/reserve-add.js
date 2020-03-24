// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var queryInstrumentByIdUrl = '/inst/detail.do?instId=';
var instumentVo;


var page = {
    init : function(){
        formError.hide();
        this.initTable();
        this.bindEvent();
    },
    initTable : function(){
        queryInstrumentByIdUrl += _common.getUrlParam("instId");
        $.getJSON(queryInstrumentByIdUrl, function(data){
            if (!data.status){
                instumentVo = data.data;
                $('#inst-name').val(instumentVo.name);
                $('#inst-model').val(instumentVo.model);
                $('#inst-location').val(instumentVo.location);
                $('#inst-lab-name').val(instumentVo.labName);
                $('#inst-status').val(_common.instEnableStatusToStr(instumentVo.enableStatus));
                $('#inst-description').val(instumentVo.description);
                $('#inst-uncompleted-times').val(instumentVo.uncompletedReserveList.length);
                $('#inst-totle-times').val(instumentVo.reserveList.length);
                var templateHtml = '';
                var uncompletedReserveList = instumentVo.uncompletedReserveList;
                $('.person-num').text(uncompletedReserveList.size);
                uncompletedReserveList.map(function(item, index){
                    var startTimeStr = item.testStartYear + '.' + item.testStartMonth + '.' + item.testStartDay + '  ' +
                        _date.toTwoDigit(item.testStartHour) + item.testStartHour + ':' + _date.toTwoDigit(item.testStartMinute) + item.testStartMinute;
                    var endTimeStr = item.testEndYear + '.' + item.testEndMonth + '.' + item.testEndDay + '  ' +
                        _date.toTwoDigit(item.testEndHour) + item.testEndHour + ':' + _date.toTwoDigit(item.testEndMinute) + item.testEndMinute;
                    templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                        '<td>' + (index + 1) + '</td><td>' + item.instName +
                        '</td><td>' + item.userRealname + '</td><td>' + startTimeStr +
                        '</td><td>' + endTimeStr + '</td></tr>';
                });
                $('tbody').append(templateHtml);
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
        // var startTimeformData = {
        //     year       : $.trim($('.start-year').val()),
        //     month      : $.trim($('.start-month').val()),
        //     day        : $.trim($('.start-day').val()),
        //     hour       : $.trim($('.start-hour').val()),
        //     minute     : $.trim($('.start-minute').val())
        // };
        // var startTime = _date.timeToDate(startTimeformData.year, startTimeformData.month, startTimeformData.day, startTimeformData.hour, startTimeformData.minute);
        //
        // var endTimeformData = {
        //     year       : $.trim($('.start-year').val()),
        //     month      : $.trim($('.start-month').val()),
        //     day        : $.trim($('.start-day').val()),
        //     hour       : $.trim($('.start-hour').val()),
        //     minute     : $.trim($('.start-minute').val())
        // };
        // var endTime = _date.timeToDate(endTimeformData.year, endTimeformData.month, endTimeformData.day, endTimeformData.hour, endTimeformData.minute);

        var formData = {
            instId              : _common.getUrlParam('instId'),
            testStartYear       : $.trim($('.start-year').val()),
            testStartMonth      : $.trim($('.start-month').val()),
            testStartDay        : $.trim($('.start-day').val()),
            testStartHour       : $.trim($('.start-hour').val()),
            testStartMinute     : $.trim($('.start-minute').val()),
            testEndYear         : $.trim($('.end-year').val()),
            testEndMonth        : $.trim($('.end-month').val()),
            testEndDay          : $.trim($('.end-day').val()),
            testEndHour         : $.trim($('.end-hour').val()),
            testEndMinute       : $.trim($('.end-minute').val())
        };

        _reserve.add(formData, function(){
            alert('预约成功');
            window.history.back(-1);
        }, function(errMsg){
            alert(errMsg);
        });
    }
};

$(function(){
    page.init();
});


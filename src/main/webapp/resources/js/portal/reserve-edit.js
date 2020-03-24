// 表单里的错误提示
var formError = {
    show : function(errMsg){
        $('.error-item').show().find('.error-msg').text(errMsg);
    },
    hide : function(){
        $('.error-item').hide().find('.error-msg').text('');
    }
};

var queryReservationByIdUrl = '/reserve/detail.do';
var listReservationByInstIdUrl = '/inst/detail.do';

var reserveVo;

var page = {
    init : function(){
        formError.hide();
        this.initCurrentInfo();
        this.initTable();
        this.bindEvent();
    },
    initCurrentInfo : function(){
        queryReservationByIdUrl += ('?reserveId=' + _common.getUrlParam("reserveId"));
        $.getJSON(queryReservationByIdUrl, function(data){
            if (!data.status){
                reserveVo = data.data;
                listReservationByInstIdUrl += ('?instId=' + reserveVo.instId);
                var startTimeStr = item.testStartYear + '.' + item.testStartMonth + '.' + item.testStartDay + '  ' +
                    _date.toTwoDigit(item.testStartHour) + item.testStartHour + ':' + _date.toTwoDigit(item.testStartMinute) + item.testStartMinute;
                var endTimeStr = item.testEndYear + '.' + item.testEndMonth + '.' + item.testEndDay + '  ' +
                    _date.toTwoDigit(item.testEndHour) + item.testEndHour + ':' + _date.toTwoDigit(item.testEndMinute) + item.testEndMinute;
                $('.real-start-time').text(startTimeStr);
                $('.real-end-time').text(endTimeStr);

                $.getJSON(listReservationByInstIdUrl, function(data){
                    if (!data.status){
                        var templateHtml = '';
                        var reservationList = data.data.uncompletedReserveList;
                        $('.person-num').text(reservationList.size);
                        reservationList.map(function(item, index){
                            templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                                '<td>' + index + 1 + '</td><td>' + reserveVo.instName +
                                '</td><td>' + reserveVo.userRealname +
                                '</td><td>' + _date.dateToStr(item.testStartTime) + '</td><td>' + _date.dateToStr(item.testEndTime) +
                                '</td>';
                        });
                        $('tbody').append(templateHtml);
                    }
                });
            }
        });

    },
    initTable : function(){

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
        //     year       : $.trim($('.end-year').val()),
        //     month      : $.trim($('.end-month').val()),
        //     day        : $.trim($('.end-day').val()),
        //     hour       : $.trim($('.end-hour').val()),
        //     minute     : $.trim($('.end-minute').val())
        // };
        // var endTime = _date.timeToDate(endTimeformData.year, endTimeformData.month, endTimeformData.day, endTimeformData.hour, endTimeformData.minute);

        var formData = {
            id                  : reserveVo.id,
            instId              : reserveVo.instId,
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

        _reserve.edit(formData, function(){
            alert('修改成功');
            window.history.back(-1);
        }, function(errMsg){
            alert(errMsg);
        });
    }
};

$(function(){
    page.init();
});



var queryInstrumentByIdUrl = '/inst/detail.do?instId=';
var instumentVo;

var page = {
    init : function(){
        this.initTable();
        this.bindEvent();
    },
    initTable : function(){
        queryInstrumentByIdUrl += _common.getUrlParam("instId");
        $.getJSON(queryInstrumentByIdUrl, function(data){
            if (!data.status){
                instumentVo = data.data;
                $('#inst-name').text(instumentVo.name);
                $('#inst-model').text(instumentVo.model);
                $('#inst-location').text(instumentVo.location);
                $('#inst-lab-name').text(instumentVo.labName);
                $('#inst-status').text(_common.instEnableStatusToStr(instumentVo.enableStatus));
                $('#inst-description').text(instumentVo.description);
                $('#inst-uncompleted-times').text(instumentVo.uncompletedReserveList.length);
                $('#inst-totle-times').text(instumentVo.reserveList.length);
                $('#inst-image').attr('src', '../../../resources/image/instrument/' + instumentVo.image);
                var templateHtml = '';
                var uncompletedReserveList = instumentVo.uncompletedReserveList;
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
        $('#reserve').click(function(){
            window.location.href = '/reserve/add?instId=' + _common.getUrlParam("instId");
        });
    }
};

$(function(){
    page.init();
});


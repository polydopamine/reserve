
var listInstrument = '/inst/list.do';


var page = {
    init : function(){
        this.intiInstTable();
        this.bindEvent();
    },
    intiInstTable : function(){
        $.getJSON(listInstrument, function(data){
            var templateHtml = '';
            var instrumentList = data.data.list;
            instrumentList.map(function(item, index){
                templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                    '<td>' + (index + 1) + '</td><td>' + item.name +
                    '</td><td>' + item.model + '</td><td>' + item.location +
                    '</td><td class="inst-detail"><a href="/inst/detail?instId=' + item.id + '">查看详情</a>' +
                    '</td><td class="reserve"><a href="/reserve/add?instId=' + item.id + '">立即预约</a></td></tr>';
            });
            $('tbody').append(templateHtml);
        });
    },
    bindEvent : function(){
    }
};

$(function(){
    page.init();
});
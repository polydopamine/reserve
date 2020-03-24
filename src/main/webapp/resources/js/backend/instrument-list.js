
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
                    '</td><td class="inst-edit"><a href="/admin/inst/edit?instId=' + item.id + '">编辑仪器详情</a>' +
                    '</td><td class="inst-delete"><a href="/admin/inst/delete.do?instId=' + item.id + '">删除</a></td></tr>';
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
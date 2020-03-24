
var listLab = '/admin/lab/list.do';


var page = {
    init : function(){
        this.intiInstTable();
        this.bindEvent();
    },
    intiInstTable : function(){
        $.getJSON(listLab, function(data){
            var templateHtml = '';
            var instrumentList = data.data.list;
            instrumentList.map(function(item, index){
                templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                    '<td>' + (index + 1) + '</td><td>' + item.name +
                    '</td><td>' + item.departName +
                    '</td><td class="inst-delete"><a href="/admin/lab/delete.do?labId=' + item.id + '">删除</a></td></tr>';
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
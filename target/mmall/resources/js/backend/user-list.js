
var listUser = '/admin/user/list.do';


var page = {
    init : function(){
        this.intiInstTable();
        this.bindEvent();
    },
    intiInstTable : function(){
        $.getJSON(listUser, function(data){
            var templateHtml = '';
            var userList = data.data.list;
            userList.map(function(item, index){
                templateHtml += '<tr class="table-body" id="' + item.id + '">' +
                    '<td>' + (index + 1) + '</td><td>' + item.username +
                    '</td><td>' + item.realname + '</td><td>' + _common.getRoleText(item.role) +
                    '</td><td>' + item.enableRole +
                    '</td><td class="inst-delete"><a href="/admin/user/edit?userId=' + item.id + '">编辑用户信息</a></td></tr>';
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
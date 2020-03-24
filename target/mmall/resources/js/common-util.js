/*
* @Author: wsh
* @Date:   2020-03-04 10:51:39
* @Last Modified by:   wsh
* @Last Modified time: 2020-03-09 15:16:34
*/
'use strict';

var conf = {
    // serverHost : 'http://localhost:8080'
    serverHost : 'http://polydopamine.icu'
};

var _common = {
    // 网络请求
    request : function(param){
        var _this = this;
        $.ajax({
            type        : param.method  || 'get',
            url         : param.url     || '',
            dataType    : param.type    || 'json',
            data        : param.data    || '',
            success     : function(res){
                // 请求成功
                if (0 === res.status){
                    typeof param.success === 'function' && param.success(res.data, res.msg);
                }
                // 没有登录，需要强制登录
                else if (10 === res.status){
                    _this.doLogin();
                }
                // 请求数据错误
                else if (1 === res.status){
                    typeof param.error === 'function' && param.error(res.msg);
                }
            },
            error       : function(err){
                typeof param.error === 'function' && param.error(err.statusText);
            }
        });
    },
    // 获取服务器地址
    getServerUrl : function(path){
        return conf.serverHost + path;
    },
    // 获取URL参数
    getUrlParam : function(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
        var result = window.location.search.substr(1).match(reg);
        return result ? decodeURIComponent(result[2]) : null;
    },
    // 渲染html模板
    renderHtml : function(htmlTemplate, data) {
        // 先编译，再渲染
        var template = Hogan.compile(htmlTemplate);
        var result = template.render(data);
        return result;
    },
    // 操作成功提示
    successTips : function(msg) {
        alert(msg || '操作成功');
    },
    // 操作失败提示
    errorTips : function(msg) {
        alert(msg || '操作失败');
    },
    // 字段验证，包括：非空、手机、邮箱
    validate : function(value, type) {
        var value = $.trim(value);
        // 非空验证
        if ('require' === type) {
            return !!value;
        }
        // 手机号验证
        if ('phone' === type) {
            return /^1\d{10}$/.test(value);
        }
        // 邮箱验证
        if ('email' === type) {
            return /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value);
        }
    },
    // 统一登录处理
    doLogin : function(){
        window.location.href = './user-login.html?redicret=' + encodeURIComponent(window.location.href);
    },
    // 跳转主页
    goHome : function() {
        window.location.href = './user-login.html';
    },
    getRoleText : function(role){
        if (role === '1' || role === 1){
            return '教师';
        } else if (role === '2' || role === 2){
            return '博士后';
        } else if (role === '3' || role === 3){
            return '工作人员';
        } else if (role === '4' || role === 4){
            return '博士生';
        } else if (role === '5' || role === 5){
            return '硕士生';
        } else if (role === '6' || role === 6){
            return '本科生';
        }
        return '无';
    },
    instEnableStatusToStr : function(enableStatus){
        if (enableStatus === -1){
            return '已报废';
        } else if (enableStatus === 0){
            return '维护中';
        } else if (enableStatus === 1){
            return '正常使用';
        }
    }
};

var _date = {
    timeToDate : function(year, month, day, hour, minute){
        return new Date(year, month - 1, day, hour, minute);
    },
    dateToTime : function(date) {
        var formDate = {
            year    : date.getFullYear(),
            month   : date.getMonth(),
            day     : date.getDay(),
            hour    : date.getHours(),
            minute  : date.getMinutes()
        };
        return formDate;
    },
    dateToStr : function(datemillsecond) {
        var date = new Date(datemillsecond);
        var str = date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDay() + "  " + date.getHours() + ":" + date.getMinutes();
        return str;
    },
    toTwoDigit : function(num) {
        if (num < 10) {
            return '0';
        }
        return '';
    }
};

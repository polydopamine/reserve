/*
* @Author: wsh
* @Date:   2020-03-02 21:42:09
* @Last Modified by:   wsh
* @Last Modified time: 2020-03-05 10:58:52
*/
// 使用严格模式
'use strict';

require('page/common/nav/index.js');
require('page/common/header/index.js');
var navSide = require('page/common/nav-side/index.js');

navSide.init({
    name : 'pass-update'
});

// console.log(util);

// var _mm = require('util/common-util.js');

// $('body').html('jquery');

// 测试网络请求工具
// _mm.request({
//     url: '/product/list.do?keyword=1',
//     success: function(res){
//         console.log(res);
//     },
//     error: function(errMsg){
//         console.log(errMsg);
//     }
// })
 
// 测试获取服务器地址
// console.log(_mm.getUrlParam('key'));

// 测试渲染html
// var html = '<div>{{data}}</div>';
// var data = {
//     data : '123'
// };
// console.log(_mm.renderHtml(html, data));

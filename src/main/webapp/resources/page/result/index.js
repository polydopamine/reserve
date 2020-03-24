/*
* @Author: wsh
* @Date:   2020-03-05 11:02:11
* @Last Modified by:   wsh
* @Last Modified time: 2020-03-05 11:25:42
*/
'use strict';
require('./index.css');
require('page/common/nav-simple/index.js');
var _mm = require('util/common-util.js');

$(function(){
    var type = _mm.getUrlParam('type') || 'default';
    var $element = $('.' + type + '-success');
    // 显示对应的提示元素
    $element.show();
})
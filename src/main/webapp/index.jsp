<!DOCTYPE html>
<html lang="en">
<head>    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>实验仪器预约系统</title>
</head>

<link rel="stylesheet" type="text/css" href="resources/css/common.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/portal/user-login.css"/>
<body>
<div class="page-wrap">
    <!--定宽布局-->
    <div class="w">
        <div class="login-con">
            <div class="user-title">用户登录</div>
            <div class="user-box">
                <div class="error-item">
                    <img src="resources/image/icon/error-red.svg" alt="" class="icon error-icon"/>
                    <span class="error-msg">Error</span>
                </div>
                <div class="user-item">
                    <img src="resources/image/icon/user.svg" alt="" class="icon"/>
                    <input type="text" class="user-content" id="username" placeholder="请输入用户名" autocomplete="off"/>
                </div>
                <div class="user-item">
                    <img src="resources/image/icon/password.svg" alt="" class="icon"/>
                    <input type="password" class="user-content" id="password" placeholder="请输入密码" autocomplete="off"/>
                </div>
                <div id="submit" class="btn btn-submit">登录</div>
                <div class="link-item">
                    <a href="/user/reset_password" class="link">忘记密码</a>
                    <a href="/user/register" class="link">立即注册</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="w">
                <div class="links">
                    <a href="https://www.isiknowledge.com/" class="link" target="_bland">Web of Science</a> |
                    <a href="http://tool.yovisun.com/scihub/" class="link" target="_bland">SCI-Hub</a> |
                    <a href="https://www.cnki.net/" class="link" target="_bland">知网</a> |
                    <a href="http://polymer.zju.edu.cn/" class="link" target="_bland">浙江大学高分子系</a> |
                    <a href="http://www.cc98.org/" class="link" target="_bland">CC98</a>
                </div>
                <p class="copyright">
                    Copyright ©2020 polydopmine.icu All Right Reserved
                </p>
            </div>
        </div>
    </div>


</div>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/portal/user-login.js"></script>
<!--<script type="text/javascript" src="../../../resources/js/portal/reserve-list.js"></script>-->
<script type="text/javascript" src="resources/js/common-util.js"></script>
<script type="text/javascript" src="resources/js/service/user-service.js"></script>
</body>
</html>

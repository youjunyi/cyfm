﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${ctx}/static/manage/css/style.css" rel="stylesheet" type="text/css" />
<style>
    .nav{float:left;}
    .nav li{float:left;width:87px;height:88px; text-align:center;}
    .nav li a{display:block;width:87px;height:88px;-moz-transition: none; transition: background-color 0.3s linear; -moz-transition: background-color 0.3s linear; -webkit-transition: background-color 0.3s linear; -o-transition: background-color 0.3s linear; }
    .nav li a.selected{background:url(${ctx}/static/manage/images/navbg.png) no-repeat;}
    .nav li a:hover{display:block;background:#000;color:#fff;background: none repeat scroll 0% 0% rgb(43, 127, 181);}
    .nav li img{margin-top:10px;}
    .nav li a{display:block;}
    .nav a h2{font-size:14px;color:#d6e8f1;}
    .nav a:hover h2{color:#fff;}
</style>
<script language="JavaScript" src="${ctx}/static/plugins/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})

</script>
</head>
<body style="background:url(${ctx}/static/manage/images/topbg.gif) repeat-x;">
    <div class="topleft">
    <a href="${ctx}/manage" target="_parent"><img src="${ctx}/static/manage/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="default.html" target="rightFrame" class="selected"><img src="${ctx}/static/manage/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="imgtable.html" target="rightFrame"><img src="${ctx}/static/manage/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="imglist.html"  target="rightFrame"><img src="${ctx}/static/manage/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="tools.html"  target="rightFrame"><img src="${ctx}/static/manage/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="computer.html" target="rightFrame"><img src="${ctx}/static/manage/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="tab.html"  target="rightFrame"><img src="${ctx}/static/manage/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="${ctx}/static/manage/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="${ctx}/exitSystem" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>admin</span>
    <i>消息</i>
    <b>5</b>
    </div>    
    
    </div>
</body>
</html>

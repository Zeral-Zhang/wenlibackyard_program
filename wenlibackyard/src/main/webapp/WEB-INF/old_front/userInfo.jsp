<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>main</title>
<meta name="description" content="" />
<meta name="author" content="ZeralZhang" />
<!-- Include meta tag to ensure proper rendering and touch zooming -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
<!-- //for-mobile-apps -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/jquery.mobile-1.4.5.min.css" rel="stylesheet"
	type="text/css" media="all" />
<!-- pignose css -->
<link href="css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<div data-role="page" data-theme="a" id="userinfo" data-title="个人信息">
		<div class="ui-content" role="main">
			<ul data-role="listview" data-inset="false">
				<li data-icon="false">
					</br> 
					<a href="userDetail" rel="external"> 
						<img src="${sessionScope.userInfo.userHeadImgUrl}" alt="用户头像">
						<h2>${sessionScope.userInfo.userNickName}</h2>
						<p>${sessionScope.userInfo.userId}</p>
					</a>
				</li>
				</br>
				<li>
					<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">我的商品</a>
				</li>
				</br>
				<li>
					<a href="productAdd" rel="external" class="ui-btn ui-icon-heart ui-btn-icon-left">添加商品</a> 
					</br>
				</li>
				<li>
					<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">购物清单</a>
				</li>
				</br>
				<li>
					<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹</a>
				</li>
				</br>
			</ul>
		</div><!-- /content -->
		<!-- footer -->
		<jsp:include page="foot.jsp"></jsp:include>
	</div><!-- /page -->
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- js -->
<script type="text/javascript" src="js/jquery.mobile-1.4.5.min.js"></script>
<!-- for bootstrap working -->
<script type="text/javascript" src="bootStrap/js/bootstrap.min.js"></script>
<!-- for bootstrap working -->
<script src="js/jquery.easing.min.js"></script>
</body>
</html>


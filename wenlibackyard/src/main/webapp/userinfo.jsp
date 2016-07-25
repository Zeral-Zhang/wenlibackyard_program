<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <s:if test="#session.snsUserInfo==null">
	<c:redirect url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7d5b5ab70d0ae347&redirect_uri=http%3A%2F%2Fwenlibackyard.ngrok.natapp.cn%2Fwenlibackyard%2Fvalidate_User&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"></c:redirect>
</s:if>
 --%>
<s:if test="#session.userInfo==null">
	<c:redirect url="${request.servletContext.contextPath}/login.jsp"></c:redirect>
</s:if>
<!DOCTYPE html>
<html lang="en">
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
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/jquery.mobile-1.4.5.min.css" rel="stylesheet"
	type="text/css" media="all" />
<!-- pignose css -->
<link href="css/pignose.layerslider.css" rel="stylesheet"
	type="text/css" media="all" />
<!-- //pignose css -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<script type="text/javascript" src="js/jquery.mobile-1.4.5.min.js"></script>
<!-- for bootstrap working -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<script src="js/jquery.easing.min.js"></script>
</head>
<body>
	<div data-role="page" data-theme="a" id="userinfo" data-title="个人信息">
		<div class="ui-content" role="main">
			<ul data-role="listview" data-inset="false">
				<li data-icon="false">
					</br> 
					<a href="userdetail.jsp" rel="external"> <img
						src="${sessionScope.userInfo.userHeadImgUrl}" alt="用户头像">
						<h2>${sessionScope.userInfo.userNickName}</h2>
						<p>${sessionScope.userInfo.userId}</p>
					</a>
				</li>
				</br>
				<li>
					<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹</a>
				</li>
				<li>
					<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹</a> 
					</br>
				</li>
				<li><a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹
				</a></li>
				<li><a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹
				</a></li>
				</br>
				<li><a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹
				</a></li>
				</br>
				<li><a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">收藏夹
				</a></li>
			</ul>
		</div>
		<!-- /content -->
		<div data-role="footer" data-position="fixed" data-id="myfooter" data-theme="a">
			<div data-role="navbar">
				<ul>
					<li><a href="list.jsp" data-transition="flip" data-icon="bars">最新列表</a>
					</li>
					<li><a href="find.html" data-transition="flip"
						data-icon="search">发现</a></li>
					<li><a href="shopcar.html" data-transition="flip"
						data-icon="shop">购物车</a></li>
					<li><a href="userinfo.jsp" data-transition="flip"
						data-icon="user">个人</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->

</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<!-- Required meta tags always come first -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>文理后院</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="<%=path %>/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="<%=path %>/css/reset.css">
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<nav class="navbar navbar-light bg-faded about_nav">
				<span class="container">个人中心</span>
			</nav>
		</div>
		<div class="about_mid">
			<ul class="list-group">
				<li class="list-group-item user_info" onclick="window.location.href='<%=path %>/toUserDetail'">
					<div class="user_info">
						<div class="user_photo">
							<img src="${sessionScope.userInfo.userHeadImgUrl}"/>
						</div>
						<div class="user_name">
							<span>${sessionScope.userInfo.userNickName}</span>
						</div>
					</div>
				</li>
				<li class="list-group-item">
					<a href="<%=path %>/toUserSaling">
						<div class="list_img1"></div>正在售卖<span class="label label-default label-pill stay_right">1</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="<%=path %>/toProductAdd">
						<div class="list_img2"></div>添加商品<span class="label label-default label-pill stay_right">2</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="<%=path %>/toUserPayed">
						<div class="list_img3"></div>我买到的<span class="label label-default label-pill stay_right">4</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="<%=path %>/toUserFavorite">
						<div class="list_img4"></div>我的收藏<span class="label label-default label-pill stay_right">5</span>
					</a>
				</li>
				
			</ul>
		</div>
		<jsp:include page="foot.jsp"></jsp:include>
		
		<script src="<%=path %>/js/jquery.min.js"></script>
		<script src="<%=path %>/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript">
			$(".nav_btn").click(function(event) {
				/* Act on the event */
				$(".nav_pane").toggle().css('height', $("body").height());
				$(".bottom").toggle();
			});
		</script>
	</body>
</html>
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
		<title>发现</title>
		<!-- Bootstrap CSS -->
		<link href="<%=path %>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/reset.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" media="all" />
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<nav class="navbar navbar-light bg-faded about_nav">
				<span class="container">发现</span>
			</nav>
		</div>
		<div class="dis_mid">
			<ul class="list-group">
				<c:forEach items="${schoolInfolst }" var="schoolInfo">
					<li class="list-group-item">
						<a href="toSchoolInfoProduct?schoolInfoId=${schoolInfo.schoolInfoId }">
							${schoolInfo.name }	
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<!-- 底部导航 -->
		<jsp:include page="foot.jsp"></jsp:include>
		
		<script src="<%=path %>/js/jquery.min.js"></script>
	</body>
</html>
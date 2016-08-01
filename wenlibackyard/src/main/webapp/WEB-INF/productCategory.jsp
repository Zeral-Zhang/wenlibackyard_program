<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>find</title>
		<meta name="description" content="">
		<meta name="author" content="Runaway">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
				<!-- //for-mobile-apps -->
		<link href="<%=path%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path%>/css/jquery.mobile-1.4.5.min.css" rel="stylesheet" type="text/css" media="all" />
		<!-- pignose css -->
		<link href="<%=path%>/css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />
		<!-- //pignose css -->
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- js -->
		<script type="text/javascript" src="<%=path%>/js/jquery-2.1.4.min.js"></script>
		<!-- //js -->
		<script type="text/javascript" src="<%=path%>/js/jquery.mobile-1.4.5.min.js"></script>
		<!-- for bootstrap working -->
		<script type="text/javascript" src="<%=path%>/js/bootstrap-3.1.1.min.js"></script>
		<!-- //for bootstrap working -->
		<script src="<%=path%>/js/jquery.easing.min.js"></script>
	</head>

	<body>

		<div data-role="page" data-title="发现">
			<div class="ui-content" role="main">

				<ul data-role="listview" data-theme="a" data-dividertheme="e" data-filter="true" data-filter-theme="a" data-filter-placeholder="查询...">
					<li data-role="list-divider">
						A
					</li>
					<li>
						<a href="list.html">中文</a>
					</li>
					<li>
						<a href="#">Alex Wickerham</a>
					</li>
					<li>
						<a href="#">Avery Johnson</a>
					</li>
					<li data-role="list-divider">
						B
					</li>
					<li>
						<a href="#">Bob Cabot</a>
					</li>
					<li data-role="list-divider">
						C
					</li>
					<li>
						<a href="#">Caleb Booth</a>
					</li>
					<li>
						<a href="#">Christopher Adams</a>
					</li>
					<li>
						<a href="#">Culver James</a>
					</li>
					<li data-role="list-divider">
						D
					</li>
					<li>
						<a href="#">David Walsh</a>
					</li>
					<li>
						<a href="#">Drake Alfred</a>
					</li>
					<li data-role="list-divider">
						E
					</li>
					<li>
						<a href="#">Elizabeth Bacon</a>
					</li>
					<li>
						<a href="#">Emery Parker</a>
					</li>
					<li>
						<a href="#">Enid Voldon</a>
					</li>
					<li data-role="list-divider">
						F
					</li>
					<li>
						<a href="#">Francis Wall</a>
					</li>
					<li data-role="list-divider">
						G
					</li>
					<li>
						<a href="#">Graham Smith</a>
					</li>
					<li>
						<a href="#">Greta Peete</a>
					</li>
					<li data-role="list-divider">
						H
					</li>
					<li>
						<a href="#">Harvey Walls</a>
					</li>
					<li data-role="list-divider">
						M
					</li>
					<li>
						<a href="#">Mike Farnsworth</a>
					</li>
					<li>
						<a href="#">Murray Vanderbuilt</a>
					</li>
					<li data-role="list-divider">
						N
					</li>
					<li>
						<a href="#">Nathan Williams</a>
					</li>
					<li data-role="list-divider">
						P
					</li>
					<li>
						<a href="#">Paul Baker</a>
					</li>
					<li>
						<a href="#">Pete Mason</a>
					</li>
					<li data-role="list-divider">
						R
					</li>
					<li>
						<a href="#">Rod Tarker</a>
					</li>
					<li data-role="list-divider">
						S
					</li>
					<li>
						<a href="#">Sawyer Wakefield</a>
					</li>
				</ul>

			</div><!-- /content -->
			<!-- footer -->
			<jsp:include page="foot.jsp"></jsp:include>
		</div><!-- /page -->

	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>500</title>
		<meta name="description" content="">
		<meta name="author" content="Runaway">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<!-- Include jQuery Mobile stylesheets -->
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
		<link rel="stylesheet" href="css/listview-grid.css">
		<!-- Include the jQuery library -->
		<script src="js/jquery-2.1.4.min.js"></script>
		<!-- Include the jQuery Mobile library -->
		<script src="js/jquery.mobile-1.4.5.min.js"></script>
		<!-- //for-mobile-apps -->
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- js -->
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		<!-- //js -->
		<!-- single -->
		<script src="js/imagezoom.js"></script>
		<script src="js/jquery.flexslider.js"></script>
		<!-- single -->
		<!-- cart -->
		<script src="js/simpleCart.min.js"></script>
		<!-- cart -->
		<!-- for bootstrap working -->
		<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
		<!-- //for bootstrap working -->
		<script src="js/jquery.easing.min.js"></script>
        <style>
			.findout{
				padding:90px 0;
				text-align: center;
			}
			
			.findout h1 {
				color: #FDA30E;
				text-align: center;
				font-size: 140px;
				margin:0 0 0.1em;
				text-transform:uppercase;
			}
			.findout p {
				font-size: 18px;
			}
			.findout .container a {
				line-height: 160px;
			}
			.findout .container a span{
				padding-right: 15px;
			}
		</style>
	</head>
	<body>

		<div data-role="page" id="productdetail">
			<div class="ui-content" role="main">
				<div class="page-head">
					<div class="container">
						<h3>Check Out</h3>
					</div>
				</div>
				<s:debug></s:debug>
				<!-- find out -->
				<div class="findout">
					<div class="container">
						<h1>500</h1>
                        <p>I'm so sorry!Page cannot be found!</p>
                        <a href="productList.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>GO BACK TO HOME</a>
					</div>
				</div>
			</div><!-- /content -->
		</div><!-- /page -->
	</body>
</html>

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

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link href="<%=path %>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/reset.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" media="all" />
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
						<h3>页面出错了</h3>
					</div>
				</div>
				<s:debug></s:debug>
				<!-- find out -->
				<div class="findout">
					<div class="container">
						<h1>500</h1>
                        <p>页面出错了(○´･д･)ﾉ        o(>﹏<)o不要啊      (°ー°〃)</p>
                        <a href="toProductList"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>返回主页</a>
					</div>
				</div>
			</div><!-- /content -->
			<jsp:include page="new_front/foot.jsp"></jsp:include>
		</div><!-- /page -->
	</body>
</html>

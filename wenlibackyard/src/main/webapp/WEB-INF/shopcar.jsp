<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>  
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

		<title>购物车</title>
		<meta name="description" content="">
		<meta name="author" content="ZeralZhang">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<!-- Include jQuery Mobile stylesheets -->
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	</head>
	<body>

		<div data-role="page" id="productdetail">
			<div class="ui-content" role="main">
				<div class="page-head">
					<div class="container">
						<h3>购物车</h3>
					</div>
				</div>
				<!-- check out -->
				<div class="checkout">
					<div class="container">
						<h3>我的购物车</h3>
						<div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s">
							<table class="timetable_sub">
								<thead>
									<tr>
										<th>删除</th>
										<th>商品</th>
										<th>数量</th>
										<th>商品名称</th>
										<th>价格</th>
									</tr>
								</thead>
								<s:iterator value="#session.mycar.items" id="productId">
									<tr class="rem1">
										<td class="invert-closeb">
										<div class="rem">
											<div class="close1"></div>
										</div>
										</td>
										<td class="invert-image"><a href="find_ProductDetail.action?productId=<s:property value="#productId.key"/>" rel="external"><img src="uppic/<s:property value="value.product.imgs"/>" alt="查看详情" class="img-responsive" /></a></td>
										<td class="invert">
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus">
													&nbsp;
												</div>
												<div class="entry value">
													<span><s:property value="value.num"/></span>
												</div>
												<div class="entry value-plus active">
													&nbsp;
												</div>
											</div>
										</div></td>
										<td class="invert"><s:property value="value.product.productName"/></td>
										<td class="invert">￥<s:property value="value.price"/></td>
									</tr>
								</s:iterator>
							</table>
						</div>
						<div class="checkout-left">

							<div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
								<a href="productList.jsp" rel="external"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续购物</a>
							</div>
							<div class="checkout-left-basket animated wow slideInLeft" data-wow-delay=".5s">
								<h4>购物清单</h4>
								<ul>
									<s:iterator value="#session.mycar.items" id="productId">
										<li>
											<s:property value="value.product.productName"/> <i>-</i><span>￥<s:property value="value.price"/></span>
										</li>
									</s:iterator>
								</ul>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<!-- //check out -->
				<div align="center">
					<a class="ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-shadow ui-btn-icon-left ui-icon-check" href="#purchase" data-rel="popup" data-position-to="window" data-transition="pop">付款</a>
				</div>
				<!-- Popups for lightbox images -->

				<div data-role="popup" id="purchase" data-overlay-theme="b" class="ui-content" style="max-width:340px; padding-bottom:2em;">
					<h3>确认购买？</h3>
					<p>
						商品详情
					</p>
					<a href="add_Order.action" class="ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-shadow ui-btn-icon-left ui-icon-check">购买: ￥${sessionScope.mycar.sumPrice}</a>
					<a href="javascript:;" class="ui-btn ui-btn-inline ui-mini ui-corner-all ui-shadow" data-rel="back">取消</a>
				</div>

			</div><!-- /content -->
			<!-- footer -->
			<jsp:include page="foot.jsp"></jsp:include>
		</div><!-- /page -->
		
	<!-- Include the jQuery library -->
	<script src="js/jquery-2.1.4.min.js"></script>
	<!-- Include the jQuery Mobile library -->
	<script src="js/jquery.mobile-1.4.5.min.js"></script>
	<!-- cart -->
	<script src="js/simpleCart.min.js"></script>
	<!-- cart -->
	<!-- for bootstrap working -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.close1').on('click', function() {
				$('.rem1').fadeOut('slow', function() {
					$('.rem1').remove();
				});
			});
		/*--quantity--*/
			$('.value-plus').on('click', function() {
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
				divUpd.text(newVal);
			});

			$('.value-minus').on('click', function() {
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;
				if (newVal >= 1)
					divUpd.text(newVal);
			});
		/*--//quantity--*/
		});
	</script>
	</body>
</html>

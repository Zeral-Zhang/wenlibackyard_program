<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<!-- Include jQuery Mobile stylesheets -->
		<link href="<%=path %>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/reset.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/alertify.css" rel="stylesheet" type="text/css" media="all" />
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<nav class="navbar navbar-light bg-faded about_nav">
				<span class="container">购物车</span>
			</nav>
		</div>
		<div class="container">
			<div class="page-head">
			</div>
			<!-- check out -->
			<div class="checkout">
				<div class="container">
					<h3>我的购物车</h3>
					<s:if test="#session.mycar == null || #session.mycar.items.isEmpty()">
						<div class="panel panel-default">
						  <div class="label-pill">您的购物车为空，请先挑选喜欢的商品。</div>
						</div
					</s:if>
					<s:else>
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
									<tr class="rem1" id="<s:property value="#productId.key"/>">
										<input value="<s:property value="value.product.price"/>" type="hidden">
										<td class="invert-closeb">
										<div class="rem">
											<div class="close1"></div>
										</div>
										</td>
										<td class="invert-image"><a href="toProductDetail.action?productId=<s:property value="#productId.key"/>" ><img src="<%=path %>/<s:property value="value.product.fileSrcs[0]"/>" data-src="holder.js/80px80p?text=走丢了Y.Y" alt="查看详情" class="img-responsive" /></a></td>
										<td class="invert">
										<div class="quantity">
											<div class="quantity-select">
												<div class="entry value-minus">
													&nbsp;
												</div>
												<div class="entry value">
													<s:property value="value.num"/>
												</div>
												<div class="entry value-plus active" data-number="<s:property value="value.product.number"/>">
													&nbsp;
												</div>
											</div>
										</div></td>
										<td class="invert"><s:property value="value.product.productName"/></td>
										<td class="invert">￥<s:property value="value.product.price"/>*<s:property value="value.num"/>=<s:property value="value.price"/></td>
									</tr>
								</s:iterator>
							</table>
						</div>
					</s:else>
					<div class="checkout-left">
					

						<div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
							<a href="<%=path %>/toProductList" ><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续购物</a>
						</div>
						<s:if test="#session.mycar != null && !#session.mycar.items.isEmpty()">
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
						</s:if>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<s:if test="#session.mycar != null && !#session.mycar.items.isEmpty()">
				<!-- //check out -->
				<div align="center">
					<a class="btn btn-success btn-default pull-left" data-toggle="modal" href="#myModal"><span class="glyphicon glyphicon-credit-card"></span>付款</a>
				</div>
			</s:if>
			<!-- Modal -->
			  <div class="modal fade" id="myModal" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <h4 class="modal-title">确认付款</h4>
			        </div>
			        <div class="modal-body">
			          <p>合计：￥${sessionScope.mycar.sumPrice}</p>
			        </div>
			        <div class="modal-footer">
			          <a href="addOrder.action" id="" class="btn btn-success btn-default">确认</a>
			          <a href="javascript:;" class="btn btn-danger btn-default" data-dismiss="modal">取消</a>
			        </div>
			      </div>
			      
			    </div>
			  </div>
		</div><!-- /content -->
		<!-- footer -->
		<jsp:include page="foot.jsp"></jsp:include>
		
	<!-- Include the jQuery library -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path %>/js/holder.min.js"></script>
	<script src="<%=path %>/js/alertify.min.js"></script>
	<!-- cart -->
	<script src="<%=path %>/js/simpleCart.min.js"></script>
	<!-- for bootstrap working -->
	<script type="text/javascript" src="<%=path %>/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.close1').on('click', function() {
				var productId = $(this).closest(".rem1").attr("id");
				$.ajax({
					   type: "get",
					   url: "${pageContext.request.contextPath}/removeFromCar.action",
					   data: "productId="+ productId,
					   success: function(msg){
						   $('#'+productId).fadeOut('slow', function() {
								 $('#'+productId).remove();
							});
					   }
					});
			});
		/*--quantity--*/
			$('.value-plus').on('click', function() {
				var productId = $(this).closest(".rem1").attr("id");
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
				if(newVal > $(this).data("number")) {
					alertify.error('商品数量超过库存！');
					return;
				}
				window.location.href = "${pageContext.request.contextPath}/changeQuantity?productId=" + productId +"&num=1";
				/* $.ajax({
					   type: "get",
					   url: "${pageContext.request.contextPath}/changeQuantity.action",
					   data: "productId="+ productId + "&num=1",
					   success: function(msg){
						   divUpd.text(newVal);
					   }
					}); */
			});

			$('.value-minus').on('click', function() {
				var productId = $(this).closest(".rem1").attr("id");
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;
				if (newVal >= 1)
					window.location.href = "${pageContext.request.contextPath}/changeQuantity?productId=" + productId +"&num=-1";
					/* $.ajax({
						   type: "get",
						   url: "${pageContext.request.contextPath}/changeQuantity.action",
						   data: "productId="+ productId + "&num=-1",
						   success: function(msg){
								divUpd.text(newVal);
						   }
						}); */
			});
		/*--//quantity--*/
		});
	</script>
	</body>
</html>

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
		<meta name="description" content="">
		<meta name="author" content="ZeralZhang.">
		<meta name="viewport" content="width=device-width; initial-scale=1.0">
		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<!-- //for-mobile-apps -->
		<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- Include jQuery Mobile stylesheets -->
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
	</head>
	<body>

		<div data-role="page" id="productdetail" data-title="商品详情">
			<div data-role="header" data-position="fixed" data-theme="a">
				<a href="productList.jsp" data-rel="back" class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">Back</a>
				<h2>商品详情</h2>
			</div><!-- /header -->
			<div class="ui-content" role="main">
				<div class="page-head">
					<div class="container">
						<h3>商品详情</h3>
					</div>
				</div>
				<!-- //banner -->
				<!-- single -->
				<div class="single">
					<div class="container">
						<div class="col-md-6 single-right-left animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
							<div class="grid images_3_of_2">
								<div class="flexslider">
									<ul class="slides">
										<li data-thumb="uppic/${requestScope.product.imgs}">
											<div class="thumb-image">
												<img src="uppic/${requestScope.product.imgs}" data-imagezoom="true" class="img-responsive">
											</div>
										</li>
										<li data-thumb="uppic/${requestScope.product.imgs}">
											<div class="thumb-image">
												<img src="uppic/${requestScope.product.imgs}" data-imagezoom="true" class="img-responsive">
											</div>
										</li>
										<li data-thumb="uppic/${requestScope.product.imgs}">
											<div class="thumb-image">
												<img src="uppic/${requestScope.product.imgs}" data-imagezoom="true" class="img-responsive">
											</div>
										</li>
										<li data-thumb="uppic/${requestScope.product.imgs}">
											<div class="thumb-image">
												<img src="uppic/${requestScope.product.imgs}" data-imagezoom="true" class="img-responsive">
											</div>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
						<div class="col-md-6 single-right-left simpleCart_shelfItem animated wow slideInRight animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInRight;">
							<h3>${requestScope.product.productName}</h3>
							<p>
								<span class="item_price">￥${requestScope.product.price}</span><del>- ￥${requestScope.product.price}</del>
							</p>
							<p>
								<span class="item_price">品牌：</span>
								<span class="item_price">${requestScope.product.brand}</span>
							</p>
							<p>
								<span class="item_price">剩余数量：</span>
								<span class="item_price">${requestScope.product.number}</span>
							</p>
							<p>
								<span class="item_price">发布时间：</span>
								<span class="item_price">${requestScope.product.pbdate}</span>
							</p>
							<p>
								<span class="item_price">发布人：</span>
								<span class="item_price">${requestScope.product.userinfo.userNickName}</span>
							</p>
							<div class="color-quality">
								<div class="color-quality-right">
									<h5>请选择购买数量 :</h5>
									<input type="range" name="slider" id="slider" value="1" min="1" max="${requestScope.product.number}">
								</div>
							</div>
							<div class="occasional">
								<h5>类型 :</h5>
								<div class="colr ert">
									<label class="radio">
										<input type="radio" name="radio" checked="">
										<i></i>大号</label>
								</div>
								<div class="colr">
									<label class="radio">
										<input type="radio" name="radio">
										<i></i>中号</label>
								</div>
								<div class="colr">
									<label class="radio">
										<input type="radio" name="radio">
										<i></i>小号</label>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="occasion-cart">
								<a id="addcar" class="item_add hvr-outline-out button2" href="javascript:;">加入购物车</a>
							</div>

						</div>
						<div class="clearfix"></div>

						<div class="bootstrap-tab animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
							<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
								<ul id="myTab" class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active">
										<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">商品描述</a>
									</li>
									<li role="presentation">
										<a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile">评论(1)</a>
									</li>
									<li role="presentation" class="dropdown">
										<a href="#" id="myTabDrop1" class="dropdown-toggle" data-toggle="dropdown" aria-controls="myTabDrop1-contents">售后保障 <span class="caret"></span></a>
										<ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1" id="myTabDrop1-contents">
											<li>
												<a href="#dropdown1" tabindex="-1" role="tab" id="dropdown1-tab" data-toggle="tab" aria-controls="dropdown1">售后</a>
											</li>
											<li>
												<a href="#dropdown2" tabindex="-1" role="tab" id="dropdown2-tab" data-toggle="tab" aria-controls="dropdown2">保障</a>
											</li>
										</ul>
									</li>
								</ul>
								<div id="myTabContent" class="tab-content">
									<div role="tabpanel" class="tab-pane fade in active bootstrap-tab-text" id="home" aria-labelledby="home-tab">
										<h5>商品详情描述</h5>
										<p>
											<span>
												${requestScope.product.context}
											</span>
										</p>
									</div>
									<div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="profile" aria-labelledby="profile-tab">
										<div class="bootstrap-tab-text-grids">
											<div class="bootstrap-tab-text-grid">
												<div class="bootstrap-tab-text-grid-left">
													<img src="images/men3.jpg" alt=" " class="img-responsive">
												</div>
												<div class="bootstrap-tab-text-grid-right">
													<ul>
														<li>
															<a href="#">我是超管</a>
														</li>
														<li>
															<a href="#"><span class="glyphicon glyphicon-share" aria-hidden="true"></span>回复</a>
														</li>
													</ul>
													<p>
														马哥树上的内裤，女生宿舍旁边一棵树上的内裤，你们懂的。
													</p>
												</div>
												<div class="clearfix"></div>
											</div>

											<div class="add-review">
												<h4>发表评论</h4>
												<form>
													<textarea type="text" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message...';}" required="">你的评价...</textarea>
													<input type="submit" value="发表">
												</form>
											</div>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown1" aria-labelledby="dropdown1-tab">
										<p>
											<span>
												正品行货
											</span>
											<span>
												本商城向您保证所售商品均为正品行货，均开具机打发票或电子发票。
											</span>
										</p>
									</div>
									<div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown2" aria-labelledby="dropdown2-tab">
										<p>
											<span>
												全国联保
											</span>
											<span>
												凭质保证书及京东商城发票，可享受全国联保服务（奢侈品、钟表除外；奢侈品、钟表由京东联系保修，享受法定三包售后服务），与您亲临商场选购的商品享受相同的质量保证。京东商城还为您提供具有竞争力的商品价格和运费政策，请您放心购买！ 
											</span>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- //single -->
			</div><!-- /content -->

		</div><!-- /page -->
		<!-- Include the jQuery library -->
		<script src="js/jquery-2.1.4.min.js"></script>
		<!-- Include the jQuery Mobile library -->
		<script src="js/jquery.mobile-1.4.5.min.js"></script>
		<!-- single -->
		<script src="js/imagezoom.js"></script>
		<script src="js/jquery.flexslider.js"></script>
		<!-- single -->
		<!-- for bootstrap working -->
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		<!-- //for bootstrap working -->
		<script src="js/jquery.easing.min.js"></script>
		<!-- FlexSlider -->
		<script>
			$(function() {
				$('.flexslider').flexslider({
					animation : "slide",
					controlNav : "thumbnails"
				});
			$("#addcar").click(function() {
				var num = $("#slider").val();
				window.location.href = "${pageContext.request.contextPath}/add_Car?productId=${product.productId}&&num=" + num + "";
			});
			});
		</script>
		<!-- //FlexSlider-->
	</body>
</html>

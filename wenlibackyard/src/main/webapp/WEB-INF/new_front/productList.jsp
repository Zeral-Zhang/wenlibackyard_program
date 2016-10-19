<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="<%=path%>/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="<%=path%>/css/reset.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
</head>
<body>
	<!-- 顶部导航 -->
	<div class="top">
		<!-- 按钮 -->
		<nav class="navbar navbar-light bg-faded">
			<a href="#" class="add_btn"><img src="<%=path%>/images/add.png" /></a>
			<div class="input-group">

				<input type="text" class="form-control" placeholder="请输入商品关键字">
				<span><a href="#" class="search_btn"><img
						src="<%=path%>/images/search.png" /></a></span>
			</div>


			<button class="navbar-toggler nav_btn" type="button">
				<img src="<%=path%>/images/icon/more.png">
			</button>
		</nav>
		<!-- 分类 -->
		<div class="nav_pane">
			<div class="container">
				<ul class="nav_ul">
					<div class="row">
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/1.jpg" /> 电脑/配件
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/2.jpg" /> 手机
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/3.jpg" /> 相机/摄像机
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/4.jpg" /> 女装
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/5.jpg" /> 男装
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/6.jpg" /> 化妆品
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/7.jpg" /> 生活用品
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/8.jpg" /> 书刊音像
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/9.jpg" /> 交通工具
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/10.jpg" /> 家用电器
						</a></li>
						<li class="col-xs-4"><a href="#"> <img
								src="<%=path%>/images/list/11.jpg" /> 珠宝首饰
						</a></li>
					</div>
				</ul>
			</div>
		</div>
		<!-- 轮播图 -->
		<div class="mid_silde">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img src="<%=path%>/images/1.jpg"
							data-src="holder.js/900x500/auto/#777:#555/text:First slide"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img src="<%=path%>/images/2.jpg"
							data-src="holder.js/900x500/auto/#666:#444/text:Second slide"
							alt="Second slide">
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span class="icon-prev"
					aria-hidden="true"></span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span class="icon-next"
					aria-hidden="true"></span>
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="horizontalTab"
			style="display: block; width: 100%; margin: 0px;">
			<ul class="resp-tabs-list">
				<li class="resp-tab-item" aria-controls="tab_item-0" role="tab">
					<span>最新发布</span>
				</li>
				<li class="resp-tab-item" aria-controls="tab_item-1" role="tab">
					<span>最多人气</span>
				</li>
				<li class="resp-tab-item" aria-controls="tab_item-2" role="tab">
					<span>随意看看</span>
				</li>
			</ul>
		</div>
		<!-- 页面中部 -->
		<div class="middle container-fluid">
			<!-- 商品卡片 -->
			<div class="mid_card">
				<div class="row card-group">
					<s:iterator value="pageBean.pagelist" var="product">
						<div class="col-xs-6">
							<div class="card">
								<a href="${pageContext.request.contextPath}/find_ProductDetail.action?productId=${product.productId}">
									<div class="card_img">
										<img class="card-img-top center-block"
											src="<%=path%>/uppic/${product.imgs}"
											data-src="holder.js/100%x180/" alt="Card image cap">
									</div>
									<div class="card-block">
										<p class="card-title">${product.productName}160/84A，zara秋冬新款，不包邮不接受议价</p>
									</div>
								</a>
								<p class="card-text">￥${product.price}</p>
							</div>
						</div>
					</s:iterator>
					<div class="col-xs-6">
						<div class="card">
							<a href="product_more.html">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/p1.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">160/84A，zara秋冬新款，不包邮不接受议价</p>
								</div>
							</a>
							<p class="card-text">￥349.00</p>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="card">
							<a href="#">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/2.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">New Balance NB 373系列男鞋复古鞋跑步鞋休闲运动鞋
										ML373GRN/浅灰色 41.5</p>
								</div>
							</a>
							<p class="card-text">￥349.00</p>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="card">
							<a href="#">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/2.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">耐克（NIKE）802611-001 AIR MAX减震 复古休闲鞋
										运动鞋 奥利奥配色 US9码</p>

								</div>
							</a>
							<p class="card-text">￥748.00</p>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="card">
							<a href="#">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/2.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">New Balance NB 373系列男鞋复古鞋跑步鞋休闲运动鞋
										ML373GRN/浅灰色 41.5</p>
								</div>
							</a>
							<p class="card-text">￥349.00</p>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="card">
							<a href="#">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/2.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">耐克（NIKE）802611-001 AIR MAX减震 复古休闲鞋
										运动鞋 奥利奥配色 US9码</p>
								</div>
							</a>
							<p class="card-text">￥748.00</p>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="card">
							<a href="#">
								<div class="card_img">
									<img class="card-img-top center-block"
										src="<%=path%>/images/product/2.jpg"
										data-src="holder.js/100%x180/" alt="Card image cap">
								</div>
								<div class="card-block">
									<p class="card-title">New Balance NB 373系列男鞋复古鞋跑步鞋休闲运动鞋
										ML373GRN/浅灰色 41.5</p>
								</div>
							</a>
							<p class="card-text">￥349.00</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部导航 -->
	<div class="bottom">
		<ul>
			<li><a href="index.html">
					<div class="link_img1"></div> 首&nbsp;&nbsp;页
			</a></li>
			<li><a href="discovery.html">
					<div class="link_img2"></div> 发&nbsp;&nbsp;现
			</a></li>
			<li><a href="#">
					<div class="link_img3"></div> 购物车
			</a></li>
			<li><a href="about_me.html">
					<div class="link_img4"></div> 关于我
			</a></li>
		</ul>
	</div>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(".nav_btn").click(function(event) {
			/* Act on the event */
			$(".nav_pane").toggle().css('height', $("body").height());
			$(".bottom").toggle();
		});
		$(function() {
			/* body... */
			var img_width = $(".card-img-top").width();
			$(".card_img").css('max-height', img_width);
		})
	</script>
</body>
</html>
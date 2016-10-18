<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="<%=path %>/css/lightgallery.css">
		<link rel="stylesheet" href="<%=path %>/css/reset.css">
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<!-- 按钮 -->
			<nav class="navbar navbar-light bg-faded pro_nav">
				<a href="index.html"><div class="go_back"></div></a>
				<div>商品详情</div>
			</nav>
		</div>
		<div class="about_mid pro_more">
			<div class="user_top">
				<div class="user_info">
					<div class="user_photo">
						<img src="<%=path %>/images/icon/p1.jpg"/>
					</div>
					<div class="user_name">
						<span>winona</span>
					</div>
				</div>
				<div class="info_right">
					<div class="price_area">
						<span class="pro_price">￥349.00</span>
					</div>
					<div class="pro_tag">
						<span class="tag_name">品牌:</span><span>zara</span>
					</div>
				</div>
			</div>
			
			<div class="product_info">
				
				<div>
					<p>衣服八成新，160/84A，zara秋冬新款，版型很棒，不接受议价，不包邮。</p>
				</div>
				<div class="price_loc">
					
					<div class="user_loc">
						<div class="loc_img"></div>
						<div class="loc_area">陕西省西安市雁塔区</div>
					</div>
				</div>
			</div>
			<div class="scroll_img">
				<div id="lightgallery">
					<a href="<%=path %>/images/product/p1.jpg">
						<img src="<%=path %>/images/product/p1.jpg">
					</a>
					<a href="<%=path %>/images/product/p2.jpg">
						<img src="<%=path %>/images/product/p2.jpg">
					</a>
					<a href="<%=path %>/images/product/p3.jpg">
						<img src="<%=path %>/images/product/p3.jpg">
					</a>
					<a href="<%=path %>/images/product/p4.jpg">
						<img src="<%=path %>/images/product/p4.jpg">
					</a>
				</div>
				
			</div>
		</div>
		<!-- 底部导航 -->
				<div class="bottom">
			<ul>
				<li>
					<a href="index.html">
						<div class="link_img1"></div>
						首&nbsp;&nbsp;页
					</a>
				</li>
				<li>
					<a href="discovery.html">
						<div class="link_img2"></div>
						发&nbsp;&nbsp;现
					</a>
				</li>
				<li>
					<a href="#">
						<div class="link_img3"></div>
						购物车
					</a>
				</li>
				<li>
					<a href="about_me.html">
						<div class="link_img4"></div>
						关于我
					</a>
				</li>
			</ul>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.js"></script>
		<script src="js/lightgallery.js"></script>
		<script type="text/javascript">
			$(".nav_btn").click(function(event) {
				/* Act on the event */
				$(".nav_pane").toggle().css('height', $("body").height());
				$(".bottom").toggle();
			});
			lightGallery(document.getElementById('lightgallery'));
		</script>
	</body>
</html>
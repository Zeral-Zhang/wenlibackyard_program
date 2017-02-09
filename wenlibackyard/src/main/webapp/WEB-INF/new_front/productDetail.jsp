<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
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
				<a href="<%=path %>/toProductList"><div class="go_back"></div></a>
				<div class="container">商品详情</div>
			</nav>
		</div>
		<div class="about_mid pro_more">
			<div class="user_top">
				<div class="user_info">
					<div class="user_photo">
						<img src="${productInfo.userInfo.userHeadImgUrl}"/>
					</div>
					<div class="user_name">
						<span>${productInfo.userInfo.userNickName}</span>
					</div>
				</div>
				<div class="info_right">
					<div class="price_area">
						<span class="pro_price">￥${productInfo.price}</span>
					</div>
					<div class="pro_tag">
						<span class="tag_name">品牌:</span><span>${productInfo.brand}</span>
					</div>
				</div>
			</div>
			
			<div class="product_info">
				
				<div>
					<p>${productInfo.context}</p>
				</div>
				<div class="price_loc">
					<div class="user_loc">
						<div class="loc_img"></div>
						<div class="loc_area">${productInfo.userInfo.userProvince}${productInfo.userInfo.userCity}</div>
					</div>
				</div>
			</div>
			<div class="scroll_img">
				<div id="lightgallery">
					<a href="<%=path%>${productInfo.fileSrcs[0]}">
						<img src="<%=path%>${productInfo.fileSrcs[0]}">
					</a>
					<a href="<%=path%>${productInfo.fileSrcs[1]}">
						<img src="<%=path%>${productInfo.fileSrcs[1]}">
					</a>
					<a href="<%=path%>${productInfo.fileSrcs[2]}">
						<img src="<%=path%>${productInfo.fileSrcs[2]}">
					</a>
					<a href="<%=path%>${productInfo.fileSrcs[3]}">
						<img src="<%=path%>${productInfo.fileSrcs[3]}">
					</a>
				</div>
				
			</div>
		</div>
		<jsp:include page="foot.jsp"></jsp:include>
		
		<script src="<%=path %>/js/jquery.min.js"></script>
		<script src="<%=path %>/bootstrap/js/bootstrap.js"></script>
		<script src="<%=path %>/js/lightgallery.js"></script>
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
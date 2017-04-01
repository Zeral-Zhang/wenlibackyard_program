<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<!-- Required meta tags always come first -->
    	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>文理后院</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="<%=path %>/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="<%=path %>/css/lightgallery.css">
		<link rel="stylesheet" href="<%=path %>/css/reset.css">
		<link rel="stylesheet" href="<%=path %>/css/weui.css">
		<link rel="stylesheet" href="<%=path %>/css/style.css">
		<link rel="stylesheet" href="<%=path %>/css/alertify.css">
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<!-- 按钮 -->
			<nav class="navbar navbar-light bg-faded pro_nav">
				<a href="javascript:history.go(-1);"><div class="go_back"></div></a>
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
			
			<div class="weui-form-preview__bd">
				 <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">类型</label>
                    <span class="weui-form-preview__value">${productInfo.productType.productTypeName }</span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">购买日期</label>
                    <span class="weui-form-preview__value"><fmt:formatDate value="${productInfo.buyDate}" type="date" pattern="yyyy-MM-dd"/></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">发布日期</label>
                    <span class="weui-form-preview__value"><fmt:formatDate value="${productInfo.pbDate}" type="date" pattern="yyyy-MM-dd"/></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">商品描述</label>
                    <span class="weui-form-preview__value">${productInfo.context }</span>
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
					<c:forEach items="${productInfo.fileSrcs }" var="img">
						<a href="<%=path%>${img}">
							<img src="<%=path%>${img}" data-src="holder.js/100px180?text=走丢了Y.Y" alt="图片走丢了">
						</a>
					</c:forEach>
				</div>
			</div>
			<div class="container" id="buyBtn">
				<button type="button" class="btn btn-success btn-block" data-toggle="modal" data-target="#myModal">加入购物车</button>
			</div>
		</div>
		
		<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">确认购买</h4>
				</div>
				<div class="modal-body">
				        <div class="container">
				           <p>请选择购买数量：</p><span class="tag_name">库存：${productInfo.number }</span>
				           <div class="input-group"> 
								<div class="quantity-select">                           
									<div class="entry value-minus">&nbsp;</div>
									<div class="entry value">1</div>
									<div class="entry value-plus active" data-number="${productInfo.number }">&nbsp;</div>
								</div>
							</div>
				        </div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="addCar" class="btn btn-success btn-default pull-left"><span class="glyphicon glyphicon-shopping-cart"></span>确认</button>
					<button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>取消</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
		
		<script src="<%=path %>/js/jquery.min.js"></script>
		<script src="<%=path %>/bootstrap/js/bootstrap.js"></script>
		<script src="<%=path %>/js/lightgallery.js"></script>
		<script src="<%=path%>/js/holder.js"></script>
		<script src="<%=path%>/js/alertify.js"></script>
		<script type="text/javascript">
			$(function() {
				var intoURI = document.referrer;
				if(intoURI.includes('toUserSaling')) {
					$('#buyBtn').remove();
				}
			});
			
			$(".nav_btn").click(function(event) {
				/* Act on the event */
				$(".nav_pane").toggle().css('height', $("body").height());
				$(".bottom").toggle();
			});
			lightGallery(document.getElementById('lightgallery'));
			
			<!--quantity-->
			$('.value-plus').on('click', function(){
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;
				if(newVal > $(this).data("number")) {
					alertify.error('商品数量超过库存！');
					return;
				}
				divUpd.text(newVal);
			});

			$('.value-minus').on('click', function(){
				var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;
				if(newVal>=1) divUpd.text(newVal);
			});
			<!--quantity-->
			
			$("#addCar").click(function() {
				var num = $(".value").text();
				window.location.href = "${pageContext.request.contextPath}/add_Car?productId=${productInfo.productId}&&num=" + num + "";
			});
		</script>
	</body>
</html>
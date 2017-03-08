<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="" />
<meta name="author" content="ZeralZhang" />
<!-- Include meta tag to ensure proper rendering and touch zooming -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
<link rel="stylesheet" href="<%=path%>/bootstrap/css/bootstrap.css" type="text/css" media="all" />
<link rel="stylesheet" href="<%=path%>/css/reset.css">
<link rel="stylesheet" href="<%=path%>/css/weui.css">
<link rel="stylesheet" href="<%=path%>/css/themes/bootstrap.min.css">
</head>
<body>
	<!-- 顶部导航 -->
	<div class="top">
		<!-- 按钮 -->
		<nav class="navbar navbar-light bg-faded about_nav">
			<a href="<%=path%>/toUserInfo"><span class="go_back"></span></a>
			<span class="container">正在售卖</span>
		</nav>
	</div>
	<div class="container">
		<div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">我发布的商品列表</div>
            <div class="weui-panel__bd">
            	<c:forEach items="${pageBean.pagelist}" var="product">
            		<a href="${pageContext.request.contextPath}/toProductDetail?productId=${product.productId}" class="weui-media-box weui-media-box_appmsg">
	                    <div class="weui-media-box__hd">
	                        <img class="weui-media-box__thumb" src="${product.fileSrcs[0]}" data-src="holder.js/100px100p?text=走丢了Y.Y" alt="图片走丢了" alt="图片走丢了">
	                    </div>
	                    <div class="weui-media-box__bd">
	                        <h4 class="weui-media-box__title">${product.productName }</h4>
	                        <p class="weui-media-box__desc">${product.context }</p>
	                        <ul class="weui-media-box__info">
		                        <li class="weui-media-box__info__meta">发布日期：${product.pbDate }</li>
		                        <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">价格：￥${product.price }</li>
		                    </ul>
	                    </div>
               		 </a>
            	</c:forEach>
            </div>
            <div id="pagination">
				<a href="toUserSaling.action?pageBean.page=${pageBean.page+1}&pageBean.totalCount=${pageBean.totalCount}" class="next">next</a>
			</div>
        </div>
	</div>
	<!-- /container -->

	<!-- Include the jQuery library -->
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=path%>/js/holder.js"></script>
	<script src="<%=path%>/js/jquery-ias.min.js"></script>
	<script>
		$(function() {
			 var ias = $.ias({
			  container:  '.weui-panel__bd',
			  item:       '.weui-media-box',
			  pagination: '#pagination',
			  next:       '.next',
			  delay: 1200
		    });
			 
		    // Add a loader image which is displayed during loading
			ias.extension(new IASSpinnerExtension());
	
			// Add a link after page 2 which has to be clicked to load the next page
			ias.extension(new IASTriggerExtension({
				text: '点击加载更多= =',
				html: '<div class="weui-panel__ft">'
					+ '<div class="weui-cell weui-cell_access weui-cell_link">'
					+ '<div class="weui-cell__bd">{text}</div>'
	                + '<span class="weui-cell__ft"></span>'
					+ '</div>'    
	            	+ '</div>',
				offset: 2
			}));
		});
	</script>
</body>
</html>

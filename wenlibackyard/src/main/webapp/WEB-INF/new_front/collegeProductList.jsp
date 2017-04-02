<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<link href="<%=path %>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/reset.css" rel="stylesheet" type="text/css" media="all" />
		<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" media="all" />
	</head>
	<body>
		<!-- 顶部导航 -->
		<div class="top">
			<!-- 按钮 -->
			<nav class="navbar navbar-light bg-faded about_nav">
				<a href="javascript:history.go(-1);"><span class="go_back"></span></a>
				<span class="container">学院商品列表</span>
			</nav>
		</div>
				<!-- 页面中部 -->
		<div class="middle container-fluid">
			<!-- 商品卡片 -->
			<div class="mid_card">
				<div class="row card-group">
					<c:forEach items="${pageBean.pagelist}" var="product">
						<div class="col-md-3 col-xs-6">
							<div class="card">
								<a href="${pageContext.request.contextPath}/toProductDetail?productId=${product.productId}">
									<div class="card_img">
										<img class="card-img-top center-block"
											src="<%=path%>${product.fileSrcs[0]}"
											data-src="holder.js/80px180?text=走丢了Y.Y" alt="图片走丢了">
									</div>
									<div class="card-block">
										<p class="card-title">${product.productName}，${product.brand }</p>
									</div>
								</a>
								<p class="card-text">￥${product.price}</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<!-- 分页插件 -->
				<div id="pagination">
					<a href="toSchoolInfoProduct.action?pageBean.page=${pageBean.page+1}&schoolInfoId=${schoolInfoId}" class="next">next</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<jsp:include page="foot.jsp"></jsp:include>
		
		<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=path%>/js/holder.js"></script>
	<script src="<%=path%>/js/jquery-ias.min.js"></script>
	
	<script type="text/javascript">
		$(function() {
			/* body... */
			var img_width = $(".card-img-top").width();
			$(".card_img").css('max-height', img_width);
			
		   var ias = $.ias({
			  container:  '.card-group',
			  item:       '.col-xs-6',
			  pagination: '#pagination',
			  next:       '.next',
			  delay: 1200
		    });
		
		    // Add a loader image which is displayed during loading
			ias.extension(new IASSpinnerExtension());

			// Add a link after page 2 which has to be clicked to load the next page
			ias.extension(new IASTriggerExtension({
				text: '点击加载更多= =', offset: 2
			}));
			
			
			// Add a text when there are no more pages left to load
			ias.extension(new IASNoneLeftExtension({
			    text: '已经到底了', // optionally
			}));
			
		  /* 
			$('#horizontalTab').easyResponsiveTabs({
				type : 'default', //Types: default, vertical, accordion
				width : 'auto', //auto or any width like 600px
				fit : true // 100% fit in a container
			}); */
		});
	</script>	
	</body>
</html>
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
			<a href="<%=path%>/toProductList"><span class="go_back"></span></a>
			<div class="input-group" style="margin: 0 auto">
		    	<input type="text" class="form-control" placeholder="请输入商品关键字" value="${search }" />
		      	<span><a href="javascript:void(0);" class="search_btn"><img src="<%=path%>/images/search.png" /></a></span>
		    </div>
		</nav>
	</div>
	<div class="container">
		<div class="page-head">
			<div class="container">
				<h3>${productType.productTypeName }</h3>
			</div>
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
					<a href="toProductCateList.action?pageBean.page=${pageBean.page+1}&productTypeId=${productTypeId}&search=${search}" class="next">next</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<jsp:include page="foot.jsp"></jsp:include>
	
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=path%>/js/holder.js"></script>
	<script src="<%=path%>/js/jquery-ias.min.js"></script>
	<script src="<%=path%>/js/masonry.pkgd.min.js"></script>
	
	<script type="text/javascript">
	
		$(".nav_btn").click(function(event) {
			/* Act on the event */
			$(".nav_pane").toggle().css('height', $("body").height());
			$(".bottom").toggle();
		});
		$('.search_btn').click(function() {
			var search = $(this).closest('.input-group').children("input").val();
			if(search.length != 0) {
				window.location.href = '${pageContext.request.contextPath}/toProductCateList.action?productTypeId=${productTypeId}&search='+search;
				var pageA = $('#pagination > a');
				pageA.attr("href", pageA.href+"?search="+search);
			} else {
				window.location.href = '${pageContext.request.contextPath}/toProductList';
			}
		});
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
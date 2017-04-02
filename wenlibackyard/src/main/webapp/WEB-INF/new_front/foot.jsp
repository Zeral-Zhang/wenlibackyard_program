<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
	<!-- 底部导航 -->
<div>
	<div style="height:60px;"></div>
	<div class="bottom">
		<ul>
			<li>
				<a href="<%=path %>/toProductList">
					<div class="link_img1"></div> 首&nbsp;&nbsp;页
				</a>
			</li>
			<li>
				<a href="<%=path %>/toDiscovery">
					<div class="link_img2"></div> 发&nbsp;&nbsp;现
				</a>
			</li>
			<li>
				<a href="<%=path %>/shopCar">
					<div class="link_img3"></div> 购物车
				</a>
			</li>
			<li>
				<a href="<%=path %>/toUserInfo">
					<div class="link_img4"></div> 关于我
				</a>
			</li>
		</ul>
	</div>
</div>
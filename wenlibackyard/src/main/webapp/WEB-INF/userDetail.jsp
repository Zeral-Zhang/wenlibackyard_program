<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<!-- Include jQuery Mobile stylesheets -->
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	</head>
	<body>
		<div data-role="page" data-theme="a" id="userdetail" data-title="用户详情">
			<div data-role="header" data-position="fixed" data-theme="a">
				<a href="userInfo"  class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">返回</a>
				<a href="#updateUserDetail" class="ui-btn ui-icon-gear ui-btn-icon-right">修改</a>
				<h2>用户详情</h2>
			</div><!-- /header -->
			<div class="ui-content" role="main">
				<ul data-role="listview" data-inset="false">
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">电话
							<p>
								<s:if test="#session.userInfo.userDetailInfo.userTel == ''">
									请补充电话信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.userTel"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">城市
							<p>
								<s:if test="#session.userInfo.userDetailInfo.userProvince == ''">
									请补充城市信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.userProvince"/>
									<s:property value="#session.userInfo.userDetailInfo.userCity"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">语言
							<p>
								${sessionScope.userInfo.userDetailInfo.userLanguage}
							</p>
							<!--用于设置国际化文件  --> 
						</a>
					</li>
					

					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">年龄
							<p>
								<s:if test="#session.userInfo.userDetailInfo.userAge == ''">
									请补充年龄信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.userAge"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">学院
							<p>
								<s:if test="#session.userInfo.userDetailInfo.schoolinfo.college == ''">
									请补充学院信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.schoolinfo.college"/>									
								</s:else>								
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">院系
							<p>
								<s:if test="#session.userInfo.userDetailInfo.schoolinfo.department == ''">
									请补充院系信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.schoolinfo.department"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">年级
							<p>
								<s:if test="#session.userInfo.userDetailInfo.schoolinfo.grade == ''">
									请补充年级信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.schoolinfo.grade"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">班级
							<p>
								<s:if test="#session.userInfo.userDetailInfo.schoolinfo.classes == ''">
									请补充班级信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userDetailInfo.schoolinfo.classes"/>									
								</s:else>
							</p>
						</a>
					</li>
				</ul>
			</div><!-- /content -->
		</div><!-- /page -->

		<div data-role="page" data-theme="a" id="updateUserDetail" data-title="信息修改">
			<div data-role="header" data-position="fixed" data-theme="a">
				<a href="#userdetail" data-rel="back" class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">返回</a>
				<h2>信息修改</h2>
			</div><!-- /header -->
			<div class="ui-content" role="main">
				<form action="<%=path%>/update_User.action" method="post">
					<input name="user.userId" value="${sessionScope.userInfo.userId}" type="hidden">
					<label for="phoneNumber">电话:</label>
					<input type="tel" name="user.userDetailInfo.userTel" id="phonenumber" placeholder="请输入电话" value="${sessionScope.userInfo.userDetailInfo.userTel}">
					<label for="userAge">年龄:</label>
					<input type="number" name="user.userDetailInfo.userAge" id="userAge" placeholder="请输入年龄" value="${sessionScope.userInfo.userDetailInfo.userAge}">
					<fieldset data-role="controlgroup" data-type="horizontal">
					    <legend>请选择您所在的城市信息:</legend>
					    <label for="province">省</label>
					    <select id="province" name="user.userDetailInfo.userProvince">
							<option selected value="-22">请选择</option>
						</select>
						<label for="city">市：</label>
						<select id="city" name="user.userDetailInfo.userCity">
							<option selected value=-22>请选择</option>
						</select>
					</fieldset>
					<fieldset data-role="controlgroup" data-type="horizontal">
					    <legend>请选择您的院系信息:</legend>
					    <label for="college">学院</label>
						<select id="college">
							<option selected value="-22">请选择</option>
							<c:forEach items="${schoolInfolst }" var="schoolInfo">
								<option value="${schoolInfo.code }">${schoolInfo.name }</option>
							</c:forEach>
						</select>
					    <select id="department" name="user.userDetailInfo.schoolInfo.code">
							<option selected value="-22">请选择</option>
						</select>
					</fieldset>
					<label for="college">学院:</label>
					<input type="text" name="user.userDetailInfo.schoolinfo.college" id="college" placeholder="请输入学院" value="${sessionScope.userInfo.userDetailInfo.schoolinfo.college}">
					<label for="department">院系：</label>
					<input type="text" name="user.userDetailInfo.schoolinfo.department" id="department" placeholder="请输入院系" value="${session.userInfo.userDetailInfo.schoolinfo.department}">
					<label for="grade">年级：</label>
					<input type="number" name="user.userDetailInfo.schoolinfo.grade" id="grade" placeholder="请输入年级" value="${session.userInfo.userDetailInfo.schoolinfo.grade}">
					<label for="classes">班级：</label>
					<input type="number" name="user.userDetailInfo.schoolinfo.classes" id="classes" placeholder="请输入班级" value="${session.userInfo.userDetailInfo.schoolinfo.classes}">
					<input type="submit" value="提交">
				</form>	
			</div><!-- /content -->
		</div><!-- /page -->
				<!-- Include the jQuery library -->
		<script src="js/jquery-2.1.4.min.js"></script>
		<!-- Include the jQuery Mobile library -->
		<script src="js/jquery.mobile-1.4.5.min.js"></script>
		<script type="text/javascript">
			$(function() {
				function objInit(obj) {		// 下拉列表框初始化
					return $(obj).html("<option value='0'>请选择</option>").selectmenu('refresh', true);
				}
				// 初始化省份信息
				$.get("initProvince_Region.action", function(data) {
					$.each(data, function(i, item) {
							$("#province").append(
									"<option value='" + item.code + "'>"
											+ item.name
											+ "</option>");
						});
					}, "json");
					// 根据省份code查找城市信息
					$("#province").change(
						function() {
							objInit("#city");
							$.get("loadCitys_Region.action",{code : $(this).val()}, function(data) {
							$.each(data, function(i, item) {
								$("#city").append(
										"<option value='" + item.code + "'>"
												+ item.name
												+ "</option>");
							});
						}, "json");
					});
					
					// 根据院code查找城系信息
					$("#college").change(
						function() {
							objInit("#department");
							$.get("loadDepartments.action",{code : $(this).val()}, function(data) {
							$.each(data, function(i, item) {
								$("#department").append(
										"<option value='" + item.code + "'>"
												+ item.name
												+ "</option>");
							});
						}, "json");
					});
					
			});
		</script>
	</body>
</html>

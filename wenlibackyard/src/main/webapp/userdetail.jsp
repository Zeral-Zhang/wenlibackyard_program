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
		<meta name="author" content="Runaway" />
		<!-- Include meta tag to ensure proper rendering and touch zooming -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico" />
		<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
		<!-- Include jQuery Mobile stylesheets -->
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
		<!-- Include the jQuery library -->
		<script src="js/jquery-2.1.4.min.js"></script>
		<!-- Include the jQuery Mobile library -->
		<script src="js/jquery.mobile-1.4.5.min.js"></script>
	</head>
	<body>
		<div data-role="page" data-theme="a" id="userdetail" data-title="用户详情">
			<div data-role="header" data-position="fixed" data-theme="a">
				<a href="userinfo.jsp"  class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">返回</a>
				<a href="#updateUserDetail" class="ui-btn ui-icon-gear ui-btn-icon-right">修改</a>
				<h2>用户详情</h2>
			</div><!-- /header -->
			<div class="ui-content" role="main">
				<ul data-role="listview" data-inset="false">
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">电话
							<p>
								${sessionScope.userInfo.userdetailinfo.userTel}
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">城市
							<p>
								${sessionScope.userInfo.userdetailinfo.userCity}
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">语言
							<p>
								${sessionScope.userInfo.userdetailinfo.userLanguage}
							</p>
							<!--用于设置国际化文件  --> 
						</a>
					</li>
					

					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">年龄
							<p>
								<s:if test="#session.userInfo.userdetailinfo.userAge == ''">
									请补充年龄信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userdetailinfo.userAge"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">学院
							<p>
								<s:if test="#session.userInfo.userdetailinfo.schoolinfo.college == ''">
									请补充学院信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userdetailinfo.schoolinfo.college"/>									
								</s:else>								
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">院系
							<p>
								<s:if test="#session.userInfo.userdetailinfo.schoolinfo.department == ''">
									请补充院系信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userdetailinfo.schoolinfo.department"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">年级
							<p>
								<s:if test="#session.userInfo.userdetailinfo.schoolinfo.grade == ''">
									请补充年级信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userdetailinfo.schoolinfo.grade"/>									
								</s:else>
							</p>
						</a>
					</li>
					<li>
						<a href="#" class="ui-btn ui-icon-heart ui-btn-icon-left">班级
							<p>
								<s:if test="#session.userInfo.userdetailinfo.schoolinfo.classes == ''">
									请补充班级信息
								</s:if>
								<s:else>
									<s:property value="#session.userInfo.userdetailinfo.schoolinfo.classes"/>									
								</s:else>
							</p>
						</a>
					</li>
				</ul>
			</div><!-- /content -->
		</div><!-- /page -->

		<div data-role="page" data-theme="a" id="updateUserDetail" data-title="信息修改">
			<div data-role="header" data-position="fixed" data-theme="a">
				<a href="./userinfo.jsp" data-rel="back" class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">返回</a>
				<h2>信息修改</h2>
			</div><!-- /header -->
			<div class="ui-content" role="main">
				<form action="<%=path%>/update_User.action" method="post">
					<input name="user.userId" value="${sessionScope.userInfo.userId}" type="hidden">
					<label for="phoneNumber">电话:</label>
					<input type="tel" name="user.userdetailinfo.userTel" id="phonenumber" placeholder="请输入电话" value="${sessionScope.userInfo.userdetailinfo.userTel}">
					<label for="userAge">年龄:</label>
					<input type="number" name="user.userdetailinfo.userAge" id="userAge" placeholder="请输入年龄" value="${sessionScope.userInfo.userdetailinfo.userAge}">
					<label for="college">学院:</label>
					<input type="text" name="user.userdetailinfo.schoolinfo.college" id="college" placeholder="请输入学院" value="${sessionScope.userInfo.userdetailinfo.schoolinfo.college}">
					<label for="department">院系：</label>
					<input type="text" name="user.userdetailinfo.schoolinfo.department" id="department" placeholder="请输入院系" value="${session.userInfo.userdetailinfo.schoolinfo.department}">
					<label for="grade">年级：</label>
					<input type="number" name="user.userdetailinfo.schoolinfo.grade" id="grade" placeholder="请输入年级" value="${session.userInfo.userdetailinfo.schoolinfo.grade}">
					<label for="classes">班级：</label>
					<input type="number" name="user.userdetailinfo.schoolinfo.classes" id="classes" placeholder="请输入班级" value="${session.userInfo.userdetailinfo.schoolinfo.classes}">
					<input type="submit" value="提交">
				</form>	
			</div><!-- /content -->
		</div><!-- /page -->
	</body>
</html>

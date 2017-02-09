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
<link href="<%=path%>/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" media="all" />
<link rel="stylesheet" href="<%=path%>/css/reset.css">
<link rel="stylesheet" href="<%=path%>/css/weui.css">
<link rel="stylesheet" href="<%=path%>/css/alertify.min.css">
<link rel="stylesheet" href="<%=path%>/css/themes/bootstrap.min.css">
</head>
<body>
	<!-- 顶部导航 -->
	<div class="top">
		<!-- 按钮 -->
		<nav class="navbar navbar-light bg-faded about_nav">
			<a href="<%=path%>/toUserInfo"><span class="go_back"></span></a>
			<span>用户详情</span>
			<a class="glyphicon glyphicon-cog" data-toggle="modal" data-target="#updateUserDetail"></a>
		</nav>
	</div>
	<div class="container">
		<div class="row" role="main">
			<ul class="list-group">
				<li class="list-group-item">
					<a href="javascript:void(0)">城市 
						<span style="float: right;"> 
							<s:property value="#session.userInfo.userProvince" /> 
							<s:property value="#session.userInfo.userCity" />
						</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">语言
						<span style="float: right;"> 
							${sessionScope.userInfo.userLanguage}
							<!--用于设置国际化文件  -->
						</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">性别
						<span style="float: right;"> 
							${sessionScope.userInfo.userGender eq 1 ? '男' : '女'}
						</span>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">
						电话
						<s:if
							test="#session.userInfo.userDetailInfo == null ||#session.userInfo.userDetailInfo.userTel == null">
							<span style="float: right;">请补充电话信息</span>
						</s:if>
						<s:else>
							<span style="float: right;"><s:property
									value="#session.userInfo.userDetailInfo.userTel" /></span>
						</s:else>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">年龄 
						<s:if test="#session.userInfo.userDetailInfo == null || #session.userInfo.userDetailInfo.userAge == null">
							<span style="float: right;">请补充年龄信息</span>
						</s:if> 
						<s:else>
							<span style="float: right;">
								<s:property value="#session.userInfo.userDetailInfo.userAge" />
							</span>
						</s:else>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">系
						 <s:if test="#session.userInfo.userDetailInfo == null || #session.userInfo.userDetailInfo.schoolInfo == null">
							<span style="float: right;">请补充学院信息</span>
						</s:if> 
						<s:else>
							<span style="float: right;">
								<s:property value="#session.userInfo.userDetailInfo.schoolInfo.name" />
							</span>
						</s:else>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">学院
						 <s:if test="#session.userInfo.userDetailInfo == null || #session.userInfo.userDetailInfo.schoolInfo == null">
							<span style="float: right;">请补充院系信息</span>
						</s:if> 
						<s:else>
							<c:forEach items="${schoolInfolst}" var="department">
								<c:if test="${userInfo.userDetailInfo.schoolInfo.PCode == department.code}">
									<span style="float: right;">${department.name}</span>
								</c:if>
							</c:forEach>
						</s:else>
					</a>
				</li>
				<li class="list-group-item">
				<a href="javascript:void(0)">年级
					 <s:if test="#session.userInfo.userDetailInfo == null || #session.userInfo.userDetailInfo.userGrade == null">
							<span style="float: right;">请补充年级信息</span>
					</s:if> 
					<s:else>
						<span style="float: right;">
							<s:property value="#session.userInfo.userDetailInfo.userGrade" />
						</span>
					</s:else>
					</a>
				</li>
				<li class="list-group-item">
					<a href="javascript:void(0)">班级
						 <s:if test="#session.userInfo.userDetailInfo ==null || #session.userInfo.userDetailInfo.userClass == null">
							<span style="float: right;">请补充班级信息</span>
						</s:if> 
						<s:else>
							<span style="float: right;">
								<s:property value="#session.userInfo.userDetailInfo.userClass" />
							</span>
						</s:else>
					</a>
				</li>
			</ul>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->

	<div id="updateUserDetail" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">修改用户信息</h4>
				</div>
				<div class="modal-body">
					<form id="userForm" action="<%=path%>/updateUser.action" method="post">
						<div class="weui-cells weui-cells_form">
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">年龄</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" value="${sessionScope.userInfo.userDetailInfo.userAge}" name="userDetail.userAge" type="number" pattern="[0-9]*"
										placeholder="请输入年龄" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="tel" value="${sessionScope.userInfo.userDetailInfo.userTel}" name="userDetail.userTel" placeholder="请输入手机号">
								</div>
							</div>
							<div class="weui-cells__title">请选择您所在的院系信息</div>
							<div class="weui-cells">
								<div class="weui-cell weui-cell_select weui-cell_select-before">
									<div class="weui-cell__hd">
										<select style="width: 200px;" class="weui-select" id="college" >
											<option value="0">请选择</option>
											<c:forEach items="${schoolInfolst }" var="schoolInfo">
												<option value="${schoolInfo.code }" ${userInfo.userDetailInfo.schoolInfo.PCode eq schoolInfo.code ? 'selected' : '' }>${schoolInfo.name }</option>
											</c:forEach>
										</select>
									</div>
									<div class="weui-cell__hd">
										<select class="weui-select" style="width: 300px;" id="department" name="userDetail.schoolInfo.code">
											<option selected value="0">请选择</option>
											<c:forEach items="${departmentlst }" var="schoolInfo">
												<option value="${schoolInfo.code }" ${userInfo.userDetailInfo.schoolInfo.code eq schoolInfo.code ? 'selected' : '' }>${schoolInfo.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">年级</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="number" pattern="[0-9]*" value="${sessionScope.userInfo.userDetailInfo.userGrade}" name="userDetail.userGrade"
										placeholder="请输入年级" />
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">班级</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" type="number" pattern="[0-9]*" value="${sessionScope.userInfo.userDetailInfo.userClass}" name="userDetail.userClass"
										placeholder="请输入班级" />
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="submit" class="btn btn-default">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>

		</div>
		<!-- /content -->
	</div>
	<!-- /page -->
	<!-- Include the jQuery library -->
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=path%>/js/alertify.min.js"></script>
	<script type="text/javascript">
		$(function() {
			function objInit(obj) { // 下拉列表框初始化
				return $(obj).html("<option value='0'>请选择</option>");
			}

			// 根据院code查找城系信息
			$("#college").change(
					function() {
						objInit("#department");
						$.get("loadDepartments.action", {
							code : $(this).val()
						}, function(data) {
							$.each(data, function(i, item) {
								$("#department").append(
										"<option value='" + item.code + "'>"
												+ item.name + "</option>");
							});
						}, "json");
					});
			// 提交表单
			$('#submit').click(function() {
				$("#userForm").submit();
			});
		});
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>Admin Login</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
  </head>
  <body id="login">
    <div class="container">
      <form class="form-signin" action="login_User.action">
        <h2 class="form-signin-heading">请	登	录</h2>
        <input type="text" class="form-control" id="userid" name="userid" placeholder="请输入昵称">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> 记住账号
        </label>
        <button id="submit" class="btn btn-large btn-primary" type="button">登陆</button>
      </form>

    </div> <!-- /container -->
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$(function() {
    		$('#submit').click(function() {
    			var userid = $('#userid').val();
    			$.post(
    				"<%=path%>/login_User.action",
    				{userid:userid},
    				huidiao
    			);
    		});
    		
    		function huidiao(data) {
    			var obj = JSON.parse(data);
    			/* 后台验证  */
    			if(obj.status == '0') {
    				alert("用户名不能为空");
    			} else if(obj.status == '-1') {
    				alert("用户名不存在");
    			} else if(obj.status == '1') {
    				alert("登陆成功");
    				 window.location.href = '<%=path%>/productList.jsp';
    			}
    		}
    	});
    </script>
  </body>
</html>
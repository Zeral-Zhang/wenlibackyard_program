<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>添加商品</title>

<meta charset="utf-8" />
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>main</title>
<meta name="description" content="" />
<meta name="author" content="ZeralZhang" />
<!-- Include meta tag to ensure proper rendering and touch zooming -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="apple-touch-icon" href="/apple-touch-icon.png" />
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link href="css/jquery.mobile-1.4.5.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="plugins/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>

<body>
	<div data-role="page" id="productAdd" data-title="添加商品">
		<div data-role="header" data-position="fixed" data-theme="a">
			<a href="./productList.jsp" data-rel="back"
				class="ui-btn ui-btn-left ui-alt-icon ui-nodisc-icon ui-corner-all ui-btn-icon-notext ui-icon-carat-l">Back</a>
			<h2>添加商品</h2>
		</div>
		<!-- /header -->
		<div class="ui-content" role="main">
			<div class="page-head">
				<div class="container">
					<h3>添加商品</h3>
				</div>
			</div>
			<div class="container">
				<form action="add_Product.action" method="post" data-ajax="false"
					enctype="multipart/form-data">
									<label for="productName">商品名称：</label>
									<input type="text" name="productinfo.productName" class="form-control"
									id="productName" placeholder="商品名称">
								<label for="brand">品牌：</label>
								<input type="text" name="productinfo.brand" class="form-control"
									id="brand" placeholder="品牌">
								<label for="buydate">购买时日期：</label>
								<div class="input-group date form_date"
										data-date="" data-date-format="yyyy-mm-dd"
										data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
										<input id="buydate" name="productinfo.buydate" class="form-control"
											size="16" type="text" value="" readonly> 
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-remove"></span>
											</span> 
											<span class="input-group-addon">
												<span class="glyphicon glyphicon-calendar"></span>
											</span>
									</div>
								<label for="price">商品价格：</label>
								<input type="number" name="productinfo.price" class="form-control"
									id="price" placeholder="商品价格">
								<label for="booktypeid">商品类别：</label>
								<select name="productinfo.producttype.productTypeId">
										<optgroup label="请选择：" id="productType">
										</optgroup>
								</select>

								<label for="state">商品状态：</label>
								<select name="productinfo.state">
										<optgroup label="请选择：">
											<option value="0">不可用</option>
											<option value="1">未上架</option>
											<option value="2">上架</option>
											<option value="3">缺货</option>
										</optgroup>
								</select>
								<label for="num">商品数量：</label>
								<input type="number" name="productinfo.number" class="form-control"
									id="num" placeholder="商品数量">
								<label for="productpic">商品图片：</label>
								<input type="file" name="productinfo.pic" class="form-control"
									id="productpic" placeholder="商品图片">
								<label for="context">商品介绍：</label>
								 	<textarea id="context" name="productinfo.context" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
								<div class="">
									<button type="submit" class="ui-shadow ui-btn ui-corner-all ui-btn-inline">提交</button>
									<button type="reset" class="ui-shadow ui-btn ui-corner-all ui-btn-inline">重置</button>
								</div>
				</form>
			</div>
		</div>
		<!-- /ui-content -->
	</div>
	<!-- jQuery 2.1.4 -->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<!-- jQueryMobile 1.4.5 -->
	<script type="text/javascript" src="js/jquery.mobile-1.4.5.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- Datatimepicker -->
	<script type="text/javascript" src="plugins/datetimepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="plugins/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- kindeditor -->
	<script type="text/javascript" src="plugins/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="plugins/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="plugins/kindeditor/plugins/code/prettify.js"></script>
    <script>
      $(function () {
      // 初始化商品类别信息
		$.get("init_ProductType.action", function(data) {
			$.each(data, function(i, item) {
					$("#productType").append(
							"<option value='" + item.productTypeId + "'>"
									+ item.productTypeName
									+ "</option>");
				});
			}, "json");
			$("form").submit(function(e){
 				 dosubmit;
			});
		var isCommitted = false;//表单是否已经提交标识，默认为false
	        function dosubmit(){
	             if(isCommitted==false){
	                 isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
	                 return true;//返回true让表单正常提交
	             }else{
	                 return false;//返回false那么表单将不提交
	             }
	         };
			$('.form_date').datetimepicker({
				language : 'zh-CN',
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				minView : 2,
				forceParse : 0
			});
			KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[id="context"]', {
				cssPath : 'plugins/kindeditor/plugins/code/prettify.css',
				uploadJson : 'plugins/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'plugins/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					autoHeightMode : true;
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['myform'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['myform'].submit();
					});
				}
			});
			prettyPrint();
		});
		});
		</script>
</body>
</html>

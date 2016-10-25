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
<link href="<%=path%>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="<%=path%>/css/reset.css">
<link rel="stylesheet" href="<%=path%>/css/weui.css">
<link rel="stylesheet" href="<%=path%>/css/alertify.min.css">
<link rel="stylesheet" href="<%=path%>/css/themes/bootstrap.min.css">
<link href="<%=path%>/plugins/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<!-- 顶部导航 -->
	<div class="top">
		<!-- 按钮 -->
		<nav class="navbar navbar-light bg-faded about_nav">
			<a href="<%=path%>/toUserInfo"><span class="go_back"></span></a>
			<span class="container">添加商品</span>
		</nav>
	</div>
	<div class="container">
		<form id="userForm" action="<%=path%>/updateUser.action" method="post">
			<input name="user.userId" value="${sessionScope.userInfo.userId}" type="hidden">
			<div class="weui-cells weui-cells_form">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">商品名称</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" placeholder="请输入商品名称" name="productInfo.productName" type="text"/>
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">品牌</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" placeholder="请输入商品品牌" name="productInfo.brand" type="text"/>
					</div>
				</div>
				<div class="weui-cell">
	                <div class="weui-cell__hd">
	                	<label for="" class="weui-label">购买日期</label>
	                </div>
	                <div class="weui-cell__bd">
	                    <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<input id="buydate" name="productInfo.buyDate" class="form-control" size="16" type="text" value="" readonly> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-remove"></span>
								</span> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
						</div>
	                </div>
           		 </div>
				<div class="weui-cell">
	                <div class="weui-cell__hd"><label class="weui-label">商品价格</label></div>
	                <div class="weui-cell__bd">
	                    <input class="weui-input" type="number" name="productInfo.price" pattern="[0-9]*" placeholder="商品价格"/>
	                </div>
           	 	</div>
           	 	<div class="weui-cells__title">商品类别</div>
				<div class="weui-cells">
					<div class="weui-cell weui-cell_select weui-cell_select-before">
						<div class="weui-cell__hd">
							<select style="width: 300px;" class="weui-select" name="productInfo.productType.productTypeId">
								<optgroup label="请选择：" id="productType">
								</optgroup>
							</select>
						</div>
					</div>
				</div>
				<div class="weui-cells__title">商品状态</div>
				<div class="weui-cells">
					<div class="weui-cell weui-cell_select weui-cell_select-before">
						<div class="weui-cell__hd">
							<select style="width: 300px;" class="weui-select" name="productInfo.state">
								<optgroup label="请选择：">
									<option value="0">不可用</option>
									<option value="1">未上架</option>
									<option value="2">上架</option>
									<option value="3">缺货</option>
								</optgroup>
							</select>
						</div>
					</div>
				</div>
				<div class="weui-cell">
	                <div class="weui-cell__hd"><label class="weui-label">商品数量</label></div>
	                <div class="weui-cell__bd">
	                    <input class="weui-input" type="number" name="productInfo.number" pattern="[0-9]*" placeholder="商品数量"/>
	                </div>
           	 	</div>
           	 	<div class="weui-gallery" id="gallery">
		            <span class="weui-gallery__img" id="galleryImg"></span>
		            <div class="weui-gallery__opr">
		                <a href="javascript:" class="weui-gallery__del">
		                    <i class="weui-icon-delete weui-icon_gallery-delete"></i>
		                </a>
		            </div>
		        </div>
           	 	 <div class="weui-cell">
	               <div class="weui-cell__bd">
	                   <div class="weui-uploader">
	                       <div class="weui-uploader__hd">
	                           <p class="weui-uploader__title">图片上传</p>
	                       </div>
	                       <div class="weui-uploader__bd">
	                           <ul class="weui-uploader__files" id="uploaderFiles">
	                           </ul>
	                           <div class="weui-uploader__input-box">
	                               <input id="uploaderInput" class="weui-uploader__input" type="file" accept="image/*" multiple />
	                           </div>
	                       </div>
	                   </div>
                	</div>
            	</div>
           	</div>
		</form>
	</div>
	
	<!-- Include the jQuery library -->
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=path%>/js/alertify.min.js"></script>
	<!-- Datatimepicker -->
	<script type="text/javascript" src="<%=path%>/plugins/datetimepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=path%>/plugins/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
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
		});
	      var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
	      $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
	      $uploaderInput = $("#uploaderInput"),
	      $uploaderFiles = $("#uploaderFiles")
	      ;
	
	  $uploaderInput.on("change", function(e){
	      var src, 
      		url = window.URL || window.webkitURL || window.mozURL, 
      		tests = {
    	      formdata: !!window.FormData
    	    }, 
    	    acceptedTypes = {
  	    	      'image/png': true,
  	    	      'image/jpeg': true,
  	    	      'image/gif': true
  	    	    },
  	    	files = e.target.files,
  	    	$fileLength = $('#uploaderFiles li').length;
	      if($fileLength > 4 || files.length > 5) {
	    	  alertify.warning('只能上传五张图片');
	      } else {
	    	  for (var i = 0, len = files.length; i < len; ++i) {
		          src = url ? url.createObjectURL(files[i]) : e.target.result;
		          if (acceptedTypes[files[i].type]) 	// 如果是规定的格式
		        	  {
		        	 	 var formData = tests.formdata ? new FormData() : null;
			        	  formData.append('upload', files[i]);
				          formData.append('uploadFileName', files[i].name);
				        //ajax异步上传  
			              $.ajax({  
			                  url: "<%=path%>/uploadFile",  
			                  type: "POST",  
			                  data: formData,
			                  dataType: "json",
			                  xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数  
			                      myXhr = $.ajaxSettings.xhr();  
			                      if(myXhr.upload){ //检查upload属性是否存在  
			                          //绑定progress事件的回调函数  
			                          myXhr.upload.addEventListener('progress',progressHandlingFunction, false);   
			                      }  
			                      return myXhr; //xhr对象返回给jQuery使用  
			                  },  
			                  success: function(result){  
		                		 $uploaderFiles.append($(tmpl.replace('#url#', src)));
		                		 alertify.success('上传成功了');
			                  },  
			                  contentType: false, //必须false才会自动加上正确的Content-Type  
			                  processData: false  //必须false才会避开jQuery对 formdata 的默认处理  
			              }); 
		        	  } else {
		        		  alertify.warning('你的格式不对呦');
		        	  }
		    	  }
	      }
	  });
	  
		//上传进度回调函数：  
	    function progressHandlingFunction(e) {  
	        if (e.lengthComputable) {  
	            $('progress').attr({value : e.loaded, max : e.total}); //更新数据到进度条  
	            var percent = e.loaded/e.total*100;  
	            $('#progress').html(e.loaded + "/" + e.total+" bytes. " + percent.toFixed(2) + "%");  
	        }  
	    } 
		
	  $uploaderFiles.on("click", "li", function(){
	      $galleryImg.attr("style", this.getAttribute("style"));
	      $gallery.fadeIn(100);
	  });
	  $gallery.on("click", function(){
	      $gallery.fadeOut(100);
	  });
		</script>
</body>
</html>
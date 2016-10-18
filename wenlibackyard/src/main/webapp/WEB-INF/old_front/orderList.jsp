<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:iterator value="mainlst" var="mainOrder">
	<s:property value="#mainOrder.orderMainId"/>
	<s:property value="#mainOrder.state"/>
	<s:date name="#mainOrder.buyDate" format="yyyy-MM-dd"/>
	<s:property value="#mainOrder.sumPrice"/>
	<br>
	<s:iterator value="#mainOrder.orderdetails" var="orderDetail">
		<s:property value="#orderDetail.productinfo.productName"/>
		<s:property value="#orderDetail.num"/>
		<s:property value="#orderDetail.sumPrice"/>
		<br>
	</s:iterator>
	<hr>
</s:iterator>

</body>
</html>
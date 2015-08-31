<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'select.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 <form name="fom" action="select_map.action" >
		地理名
		<input type="text" name="place_name" size="30" />		
		城市级别
		<select name="ranks" size="1"  >
	<option >0</option>	
	<option >1</option>
	<option >2</option>
	<option >3</option>
	<option >4</option>
	<option>5</option></select>
		所属类型
		<input type="text" name="type" size="30" />
		<s:submit value="搜索"  method="select_map"/>
		<br />
		</form>
  </body>
</html>

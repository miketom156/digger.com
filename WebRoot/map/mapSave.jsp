<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 

  <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <div><s:form action="savaMap.action" method="post" >
	<table height="290" border="1" bordercolor="#99FFFF" width="668">
	  <tr><td width="80">地理名</td>
	<td colspan="2"><input name="map_log_lat.place_name" type="text" class="sidebarHeader" value="" size="30" height="20"/></td></tr>
	<tr><td>经度</td><td colspan="2"><input name="map_log_lat.map_longitude" type="text" class="sidebarHeader" value="" size="30" height="20"/></td></tr>
	<tr><td>纬度</td><td colspan="2"><input name="map_log_lat.map_latitude" type="text" class="sidebarHeader" size="30" height="20"/></td></tr>
	<tr><td>详细地址</td><td colspan="2"><input name="map_log_lat.address" type="text" class="sidebarHeader" size="30" height="20"/></td></tr>
	<tr><td>位置类型</td><td colspan="2"><input name="map_log_lat.type" type="text" class="sidebarHeader" size="30" height="20"/></td></tr>
	<tr><td>上级地理名</td><td colspan="2"><input name="map_log_lat.last_place" type="text" class="sidebarHeader" size="30" height="20"/></td></tr>
	<tr><td >城市级别</td>
	<td colspan="2"><select name="map_log_lat.ranks" size="1" class="sidebarHeader"  >
	<option >1</option>
	<option >2</option>
	<option >3</option>
	<option >4</option>
	<option>5</option></select>
	</td></tr>
	<tr><td></td><td width="116"><input type="submit" name="ok" value="提交" /></td><td width="228"><input type="reset"  value="重置" /></td></tr>
	</table>
	<s:fielderror > message</s:fielderror>
	</s:form>
	</div>
  </body>
</html>

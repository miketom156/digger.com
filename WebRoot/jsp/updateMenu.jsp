<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="updateMenu.action" name="post">
<table border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
  <tr><td height="38">
菜单名</td>
<td>
<input name="menu.menu_name" type="text" class="sidebarHeader" value="${sessionScope.menu.menu_name}" size="40" /></td></tr><tr><td height="39">

父母编号</td>
<td>
<input name="menu.parent" type="text" class="sidebarHeader" value="${sessionScope.menu.parent}" size="40" />
</td></tr><tr><td height="39">
url</td>
<td>
<input name="menu.url" type="text" class="sidebarHeader" value="${sessionScope.menu.url}" size="40" />
</td></tr><tr><td height="32">
菜单类型</td>
<td>
<select size="1" class="sidebarHeader">
  <option>${sessionScope.menu.menu_type}</option><option>root</option><option>folder</option><option>node</option></select>
</td></tr><tr><td height="36"></td>
<td>
<input name="ok" type="submit" class="dingbat" value="提交" />
<input name="reset" type="reset" class="dingbat" value="重置" /></td></tr></table>
</form>
</body>
</html>
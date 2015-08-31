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

<form action="savaMenu.action" method="post">
<table border="2" bordercolor="#9966FF">
  <tr><td height="34">
菜单名 </td>
<td>
<input name="menu.menu_name" type="text" class="sidebarHeader" size="40" /></td>
</tr><tr><td height="29">
根目录</td>
<td>
<input name="menu.parent" type="text" class="sidebarHeader" value="0" size="40"/></td>
</tr><tr><td height="32">
url</td>
<td>
<input name="menu.url" type="text" class="sidebarHeader" size="40" /></td>
</tr><tr><td height="33">
菜单类型</td>
<td>
<select name="menu.menu_type" size="1" class="sidebarHeader">
  <option>node</option><option>folder</option><option>root</option></select>
</td></tr><tr><td height="31"></td>
<td><s:fielderror>*</s:fielderror></td></tr>
<tr><td height="44"></td>
<td>
<input name="ok" type="submit" class="dingbat" value="提交" />
<input name="reset" type="reset" class="dingbat" value="重置" /></td></tr></table>
</form>
</body>
</html>

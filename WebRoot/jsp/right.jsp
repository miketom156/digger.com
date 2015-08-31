<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="savaRight.action" method="post">
<table border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
  <tr><td width="58" height="41">
角色名</td>
<td width="133">
<select name="role.role_name" size="1" class="sidebarHeader" >
<s:iterator value="#request.roles" id="roles">
<option>
<s:property value="#roles.role_name"/>
			</option>
</s:iterator>
		</select>
	  </td></tr><tr><td height="38">
菜单名</td>
	  <td>
<select name="menu.menu_name" size="1" class="sidebarHeader" >
<s:iterator value="#request.menus" id="menus">
		<option>
		<s:property value="#menus.menu_name"/>
			</option>
</s:iterator>
		</select></td></tr><tr><td height="35"></td>
		<td>
		<s:fielderror>*</s:fielderror>
		</td></tr><tr><td height="48"></td>
		<td>
		<input name="ok" type="submit" class="dingbat" value="??" />
  </td></tr></table>
</form>
</body>
</html>

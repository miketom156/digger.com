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
<form>
<table width="362" border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
  <tr><td width="66" height="32">
角色名</td>
<td width="213">${requestScope.r.role_name}</td>
  </tr><tr><td height="32">
菜单名</td>
<td>${requestScope.m.menu_name}</td></tr></table>
</form>
</body>
</html>

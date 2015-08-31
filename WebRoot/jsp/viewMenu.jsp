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
<form><table border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
<tr><td><div align="left">菜单编号</div></td><td><div align="left">${sessionScope.menu.menu_id}</div></td></tr>
  <tr><td width="60" height="28">
    <div align="left">菜单名</div></td>
<td width="217">
  <div align="left">${sessionScope.menu.menu_name}</div></td>
</tr><tr><td height="29">
  <div align="left">父母编号</div></td>
<td>
  <div align="left">${sessionScope.menu.parent}  </div></td></tr><tr><td height="33">
    <div align="left">url</div></td>
<td>
  <div align="left">${sessionScope.menu.url}  </div></td></tr><tr><td height="41">
    <div align="left">菜单类型</div></td>
<td>
  <div align="left">${sessionScope.menu.menu_type}  </div></td></tr></table>
</form>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action=".action" method="post">
<table border="2" bordercolor="#9933FF" bgcolor="#99CCFF">
  <tr><td width="80" height="42">
支付密码</td>
<td width="170"><input name="pay.payword" type="password" class="sidebarHeader"/>
  </td>
</tr><tr><td height="35"></td>
<td>
<s:fielderror>*</s:fielderror></td></tr>
<tr><td height="38"></td>
<td>
<input name="ok" type="submit" class="title" value="确认支付" size="30"/></td></tr></table>
</form>
</body>
</html>

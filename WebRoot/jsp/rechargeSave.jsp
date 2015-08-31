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
<form action="saveRecharge.action" method="post">
<table border="2" bordercolor="#FFFFFF" bgcolor="#99CCFF">
  <tr><td width="79" height="42">
充值卡号</td>
<td width="186"><input name="recharge.recharge" type="text" class="sidebarHeader" /></td></tr><tr><td height="34">
金额</td>
<td><select name="recharge.money" class="sidebarHeader">
<option>20</option>
<option>30</option>
<option>50</option>
<option>100</option>
</select></td></tr><tr><td height="38"></td>
<td>
<s:fielderror>*</s:fielderror>
</td></tr><tr><td height="45"></td>
<td>
<input name="提交" type="submit" class="dingbat" value="提交" />
<input type="reset" class="dingbat" value="重置" /></td></tr></table>
</form>
</body>
</html>

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
<form action="updatePayword.action" method="post">
<table border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
  <tr><td width="88" height="32">
原密码</td>
<td width="215"><input name="words" type="password" class="sidebarHeader"/></td></tr><tr><td height="35">
支付密码</td>
<td><input name="pay.payword" type="password" class="sidebarHeader"/></td></tr><tr><td height="36">
确认密码</td>
<td><input name="payword" type="password" class="sidebarHeader"/></td></tr><tr><td height="37"></td>
<td>
<s:fielderror>*</s:fielderror>
</td></tr><tr><td height="40"></td>
<td>
<input name="提交" type="submit" class="dingbat" value="提交" />
<input type="reset" class="dingbat" value="重置" /></td></tr></table>
</form>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=request.getContextPath()%>/css/3.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="updateRole.action" method="post">
<table border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
  <tr><td width="80" height="34">
角色号</td>
<td width="193">${sessionScope.role_id}</td>
</tr><tr><td height="42">
角色名</td>
<td><input name="role.role_name" type="text" class="sidebarHeader" value="${sessionScope.role_name}" />
</td></tr><tr><td height="35"></td>
<td>
<input name="提交" type="submit" class="dingbat" value="提交" />
<input type="reset" class="dingbat" value="重置" /></td></tr></table>
</form>
</body>
</html>
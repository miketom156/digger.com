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
<form action="backup.action" method="post">
  <table bordercolor="#99CCCC">
    <tr><td width="54" height="32">选择表</td>
  <td width="280">
    <select name="dateMaintain.table_name" class="sidebarHeader">
      <s:iterator value="#session.table"id="table" >
        <option>
        <s:property value="#table.Tables_in_digger"/>
        </option>
      </s:iterator>
    </select></td><td width="101">
    <s:fielderror>*</s:fielderror>
</td></tr><tr><td height="39">备份名</td>
<td>
    <input name="dateMaintain.date_name"  type="text" class="sidebarHeader" size="40" height="20"/>
    </td><td> </td></tr><tr><td height="36"></td>
    <td>
    <input name="ok" type="submit" class="dingbat" value="备份" size="30"/>
  </td><td> </td></tr>
</table>
</form>
</body>
</html>
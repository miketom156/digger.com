<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    

  <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
   <form action="payTrading.action" method="post">
   <table width="299" height="115" border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
     <tr><td>支付密码</td><td><input name="passwords" type="text" class="sidebarHeader" size=10/>
   </td></tr>
   <tr><td></td><td><s:fielderror>*</s:fielderror></td></tr>
   <tr><td></td><td>
   <input type="submit" class="dingbat" value="确认支付"/>
   </td></tr>
   </table>
   </form>
  </body>
</html>

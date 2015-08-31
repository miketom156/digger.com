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
       <form >
    <div>
    <c:if test="${sessionScope.mescount!=0}">
	<a href="selectMes.action?message.isn_read=0&message.type=receiving">您有${sessionScope.mescount}封未读信件</a>|<a href="selectMesAll.action">显示全部</a></c:if>
    <table border="1" bordercolor="#99CCCC" bgcolor="#DCDCDC">
    <tr><td width="44" height="46">写信人</td><td width="674" colspan="2">${sessionScope.message.send}</td>
    </tr>
    <tr><td height="41">主题</td>
    <td colspan="2">${sessionScope.message.title}</td>
    </tr>
    <tr><td>内容</td></tr>
    <tr><td height="284" colspan="3"><textarea name="textarea" cols="100" rows="20" class="sidebarHeader">${sessionScope.message.content}</textarea></td>
    </tr>
    </table>
    </div>
    </form>
  </body>
</html>

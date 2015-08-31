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
    <form action="sendMessage.action" method="post">
    <div>
    <table border="2" bordercolor="#9933FF" bgcolor="#99CCFF">
    <tr><td width="73" height="41"><input type="submit" class="sidebarHeader" value="发送"/>
    </td><td width="630"><input type="reset" class="sidebarHeader" value="重置"/></td></tr>
    <tr><td height="46">收信人</td><td width="630" colspan="2">
	<input name="send" type="text" class="sidebarHeader" size="80" height="20"/>
	</td>
    </tr>
    <tr><td height="41">主题</td>
    <td colspan="2">
	<input name="message.title" type="text" class="sidebarHeader"  size="80" height="20"/>	</td></tr>
    <tr><td>内容</td></tr>
    <tr><td height="284" colspan="3"><textarea name="message.content" cols="100" rows="20" class="sidebarHeader"></textarea></td>
    </tr>
    </table>
    </div>
    <c:if test="${requestScope.error!=null}">
    <script language="javascript">
    alert("${requestScope.error}");
    </script>
    </c:if>
    </form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utr-8" />
<title>无标题文档</title>
</head>

<frameset rows="150,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="../main.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
<frameset rows="*" cols="225,*" framespacing="0" frameborder="no" border="0">
    <frame src="../dtree/leftTree.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="../main.html" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes></html>
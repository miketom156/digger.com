<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>digger登录</title>
		<link href="<%=request.getContextPath()%>/css/login.css"
			rel="stylesheet" type="text/css" />

	    <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		
				<div id="MainBody">
					<!--主体内容-->

					<form action="login.action">
						<table width="653">
							<tr>
								<td width="291" height="302">
									<table width="273" height="300">
										<tr>
											<td width="265">
												<s:fielderror>@</s:fielderror>
										  </td>
										</tr>
								  </table>
								</td>
								<td width="268">
									<table width="268" height="100">
										<tr>
											<td width="52">
												<span id="font">登&nbsp;&nbsp;录</span>											</td>
											<td colspan="2">
												<input name="user.user_name" type="text" class="sidebarHeader" width="200"
													height="20" />
											</td>
										</tr>
										<tr>
											<td>
												<span id="font">密&nbsp;&nbsp;码</span>
											</td>
											<td colspan="2">
												<input name="user.user_password" type="password" class="sidebarHeader" width="200"
													height="20" />
											</td>
										</tr>
										<tr>
											<td></td>
											<td width="94">
												<input name="ok" type="submit" class="dingbat" id="button" value="登录" />
										  </td>
											<td width="106">
												<input name="reset" type="reset" class="dingbat" id="button" value="重置" />
										  </td>
										</tr>
								  </table>
								</td>
								<td width="78">
									<table>
										<tr>
											<td>
												<span id="error"><s:fielderror>**</s:fielderror>
												</span>
											</td>
										</tr>
									</table>
							  </td>
							</tr>
					  </table>
					</form>

				</div>
			
			

	</body>
</html>

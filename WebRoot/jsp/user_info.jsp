<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		
		<title></title>
		<link href="<%=request.getContextPath()%>/css/login.css"
			rel="stylesheet" type="text/css" />
		
		<script  type="text/javascript" language="javascript"
			src="<%=request.getContextPath()%>/js/native.js"></script>
		<script type="text/javascript" language="javascript" >

</script>



        <link href="<%=request.getContextPath()%>/css/3.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center"><a href="<%=request.getContextPath()%>/jsp/updateUser_info.jsp">修改</a>
</div>
<form action="" method="get" name="f"><div align="center"> 
</div><table width="489" height="360" border="2" align="center" bordercolor="#9900FF" bgcolor="#99CCFF">
							<tr>
								<td width="76" height="37">
									<span id="font">用户名</span><br /></td>
								<td colspan="2">
									<s:property value="#session.user_info.user_name" />
								</td>
							</tr>
							<tr>
								<td height="40">
									<span id="font">性别</span>								</td>
								<td colspan="2">
									<s:property value="#session.user_info.sex" />
								</td>
							</tr>
							<tr>
								<td height="35">
									<span id="font">生日</span>
								</td>
								<td colspan="2">
								${sessionScope.user_info.brithday}</td>
						  </tr>
						 <tr><td height="38">年龄</td>
						 <td colspan="2"><s:property value="#session.user_info.age" /></td>
						 </tr>
							<tr>
								<td height="40"><span id="font">籍贯</span>
							  </td>
								<td colspan="2">
				<s:property value="#session.user_info.natives" />
							  </td>
							</tr>
							<tr><td height="36">电话</td>
							<td colspan="2"><s:property value="#session.user_info.tel" />
							</td></tr>
							<tr><td height="36">e-mail</td>
							<td colspan="2"><s:property value="#session.user_info.e_mail" />
							</td></tr>
							<tr><td height="41">兴趣</td>
							<td colspan="2"><s:property value="#session.user_info.hoody" />
							</td></tr>
							
  </table>
</form>
				

</body>
</html>
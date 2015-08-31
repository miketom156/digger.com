<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>digger.com</title>
		<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css" />
	    <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
	</head>


	<body>
    <br />
    <br />
    <span id="font">在此页面您将更改新的密码，请请慎重操作！</span>	    
    <div align="left"><div align="left"> 
			</div><form action="updatePassword.action" name="update" method="post"><div align="left"> 
				</div><table width="536" border="2" bordercolor="#9900FF" bgcolor="#99CCFF" height="190">
				<tr>
						<td width="136" height="34" align="left">
							<span id="font">原密码</span>						</td>
						<td width="235">
							<s:password name="user.user_password" />
				  </td>
						<td width="141">
							<span id="font"> 
							
						  </span>
				  </td>
				  </tr>
					<tr>
						<td width="136" height="34" align="left">
							<span id="font">新密码</span>						</td>
						<td width="235">
							<s:password name="password" />
					  </td>
						<td width="141">
							<span id="font"> 
							
						  </span>
					  </td>
					</tr>
					<tr>
						<td height="34" align="left">
							<span id="font">确认密码</span>
						</td>
						<td>
							<s:password name="new_password" />
						</td>
						<td>
							<span id="font">
							</span>
						</td>
					</tr>
					<tr>
						<td height="47"></td>
						<td>
							<input type="submit" name="ok" value="更改" id="button" />
							<input type="reset" name="reset" value="重置" id="button" />
						</td>
					</tr>
			  </table>
			  <s:fielderror>*</s:fielderror>
			</form>
	</div>
	</body>
</html>

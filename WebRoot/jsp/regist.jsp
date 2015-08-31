<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
		<title>digger.com注册</title>
		<link href="<%=request.getContextPath()%>/css/login.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/showDate.js"></script>
			<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/checkBlank.js"></script>
		<script  type="text/javascript" language="javascript"
			src="<%=request.getContextPath()%>/js/native.js"></script>
		<script type="text/javascript" language="javascript" >

</script>

	    <link href="../css/main.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		
				<div id="MainBody">
					<div style="height: 100px"></div>
					<form action="regist.action" method="post" name="f"><div align="left"> 
						</div><table width="691" height="259" border="2" align="center" bordercolor="#9900FF" bgcolor="#99CCFF">
							<tr>
								<td width="104" height="37" align="left">
									<span class="subtitle" id="font">用户名</span>								</td>
								<td colspan="2" align="left">
									<input name="user_name" type="text" class="sidebarHeader" id="user_name" onblur="checkBlank(this)" width="200" height="20"/>								</td>
								<td width="288"><span id="span_1" class="red"></span>
							  <s:fielderror>**</s:fielderror></td>
							</tr>
							<tr>
								<td height="32" align="left" nowrap="nowrap">
									<span class="subtitle" id="font">性别</span>								</td>
								<td colspan="2" align="left" nowrap="nowrap">
									<input type="radio" name="sex" value="1" checked="checked" />
									<label for="radio3">
										<span class="subtitle">男									</span></label>
									<span class="subtitle">
									<input type="radio" name="sex" value="2" />
									<label for="radio4">
										女									</label>
                                    </span></td>
							</tr>
							<tr>
								<td height="34" align="left">
									<span class="subtitle" id="font">生日</span>								</td>
								<td colspan="2" align="left" nowrap="nowrap">
									<select name="year"  size="1" class="sidebarHeader" id="tYEAR"onblur="HQDate()"
										onchange="createSelect()">
									</select>
									<span class="subtitle">									年</span>
								  <select name="month" size="1" class="sidebarHeader"  id="tMON"onblur="HQDate()"
										onChange="createSelect()">
									</select>
								  <span class="subtitle">									月</span>
								  <select name="day" size="1" class="sidebarHeader"   id="tDAY" onblur="HQDate()">
									</select>
								  <span class="subtitle">									日</span>								  <script type="text/javascript" >
	createSelect(1);
	showDate(new Date(2005, 03, 06));
</script>							  </td>
								<td><span id="span_4"class="red"></span></td>
							</tr>
							<tr>
								<td align="left">
									<span class="subtitle" id="font">籍贯</span>								</td>
								<td colspan="2" align="left" nowrap="nowrap">
								<select name="s1" class="sidebarHeader" onchange="chgs1()"></select>
								<span class="subtitle">省</span>
								<select name="s2" class="sidebarHeader"></select>
							  <span class="subtitle">市</span>							  <script charest=UTF-8 type="text/javascript" language="javascript">
								for(i in t1)
								f.s1.options[i]=new Option(t1[i].name,t1[i].name);
								for(i in t1[0].lv2)
								f.s2.options[i]=new Option(t1[0].lv2[i].name,t1[0].lv2[i].name);
								function chgs1(){
								var n=f.s1.selectedIndex;
    n=encodeURI(encodeURI(n));
								
								f.s2.length=t1[0].lv2.length;
								for(i in t1[n].lv2)
								f.s2.options[i]=new Option(t1[n].lv2[i].name,t1[n].lv2[i].name);
								}
								</script>							  </td>
							</tr>
							<tr>							</tr>
							<tr>
								<td height="36" align="left">
									<span class="subtitle" id="font">密码</span>								</td>
								<td colspan="2" align="left">
									<input name="user_password" type="password" class="sidebarHeader" id="password"onblur="checkBlank(this)" width="200"
										height="20" />								</td>
								
								<td><span id="span_2"class="red"></span><s:fielderror>@</s:fielderror></td>
							</tr>
							<tr>
								<td height="39" align="left">
								<span id="font"> <span class="subtitle">确认密码</span></span></td>
								<td colspan="2" align="left">
									<input name="user_password2" type="password" class="sidebarHeader" id="new_password" onblur="check()" width="200"
										height="20"/>								</td>
								<td><span id="span_3"class="red"></span></td>
							</tr>
							<tr>
								<td height="35"></td>
								<td width="82">
							  <input name="ok" type="submit" class="dingbat" id="button" value="注册" />							  </td>
								<td width="187">
							  <input type="reset" class="dingbat" id="button" value="重置" />							  </td>
							</tr>
					  </table>
					</form>
				</div>


	</body>
</html>
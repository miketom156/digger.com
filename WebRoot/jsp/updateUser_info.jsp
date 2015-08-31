<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title></title>
		<link href="<%=request.getContextPath()%>/css/login.css"
			rel="stylesheet" type="text/css" />

		<script type="text/javascript" language="javascript"
			src="<%=request.getContextPath()%>/js/native.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/checkBlank.js"></script>

		<script language=javascript
			src="<%=request.getContextPath()%>/js/date.js"></script>
			
		<script>
var oCalendarChs=new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
	    <link href="<%=request.getContextPath()%>/css/3.css" rel="stylesheet" type="text/css" />
	</head>
	<body>

		<form action="updateUser_info.action" method="post" name="f">
			<table width="489" height="360" border="2" align="left" bordercolor="#9900FF" bgcolor="#99CCFF">
				<tr>
					<td width="51" height="37">
						<span id="font">用户名</span>					</td>
					<td colspan="2">
						<s:property value="#session.user_info.user_name" />
					</td>
				</tr>
				<tr>
					<td height="40">
						<span id="font">性别</span>
					</td>
					<td>
						<s:property value="#session.user_info.sex" />
					</td>
					<td>
						<input type="radio" name="sex" value="1" checked="checked" />
						<label for="radio3">
							男
						</label>
						<input type="radio" name="sex" value="2" />
						<label for="radio4">
							女
						</label>
					</td>
				</tr>
				<tr>
					<td height="35">
						<span id="font">生日</span>
					</td>
					<td colspan="2">
					<input name="brithday"  type="text" class="sidebarHeader" id="date" onclick="getDateString(this,oCalendarChs)" 
					value="${sessionScope.user_info.brithday}"size="15">
					</td>
				</tr>
				<tr>
					<td height="40">
						<span id="font">籍贯</span>
					</td>
					<td colspan="2">${sessionScope.user_info.natives}
						<select name="s1" class="sidebarHeader" onchange="chgs1()">
					  </select>
						省
						<select name="s2" class="sidebarHeader" value="${sessionScope.user_info.city}">
					  </select>
						市
						<script charest=UTF-8 type="text/javascript" language="javascript">
	for (i in t1)
		f.s1.options[i] = new Option(t1[i].name, t1[i].name);
	for (i in t1[0].lv2)
		f.s2.options[i] = new Option(t1[0].lv2[i].name, t1[0].lv2[i].name);
	function chgs1() {
		var n = f.s1.selectedIndex;
		n = encodeURI(encodeURI(n));

		f.s2.length = t1[0].lv2.length;
		for (i in t1[n].lv2)
			f.s2.options[i] = new Option(t1[n].lv2[i].name, t1[n].lv2[i].name);
	}
</script>
					</td>
				</tr>
				<tr>
					<td height="36">
						电话
					</td>
					<td colspan="2">
						<input name="tel" type="text" class="sidebarHeader" value="${sessionScope.user_info.tel}" size="20" />
					</td>
				</tr>
				<tr>
					<td height="36">
						e-mail
					</td>
					<td colspan="2">
						<input name="e_mail" type="text" class="sidebarHeader" value="${sessionScope.user_info.e_mail}"size="20" />
					</td>
				</tr>
				<tr>
					<td rowspan="3" height="41">
						兴趣
					</td>
					<td><s:property value="#session.user_info.hoody"/></td>
					<td rowspan="2" colspan="2">
						<input name="hoody" type="checkbox" class="sidebarHeader" value="看书" />
						看书
						<input name="hoody" type="checkbox" class="sidebarHeader" value="烹饪" />
						烹饪
						<input name="hoody" type="checkbox" class="sidebarHeader" value="逛街" />
						逛街
						<input name="hoody" type="checkbox" class="sidebarHeader" value="运动" />
						运动
						<input name="hoody" type="checkbox" class="sidebarHeader" value="旅游" />
						旅游
						<input name="hoody" type="checkbox" class="sidebarHeader" value="网游" />
						网游
						<input name="hoody" type="checkbox" class="sidebarHeader" value="音乐" />
						音乐
						<input name="hoody" type="checkbox" class="sidebarHeader" value="购物" />
						购物
					</td>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td height="35"></td>
					<td width="102">
						<input name="ok" type="submit" class="dingbat" id="button" value="提交" />
				  </td>
					<td width="312">					</td>
				</tr>
		  </table><s:fielderror>*</s:fielderror>
		</form>


	</body>
</html>
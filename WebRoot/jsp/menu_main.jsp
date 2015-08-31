<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

	<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
	</head>
<SCRIPT language=JavaScript>

function selectAll(){
	var obj = document.fo.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "menuId"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fo.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "menuId"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
//当前页
var currentPage=parseInt("${requestScope.menuinfo.currentPage}");
//每页记录数
var pageSize=parseInt("${requestScope.menuinfo.pageSize}");
//总页数
var totalPage=parseInt("${requestScope.menuinfo.totalPage}");
//实现分页跳转
function goToPage(flag)
{
	if(flag=="first")
	{
		currentPage=1;
	}
	if(flag=="previous")
	{
		if(currentPage==1)
		{
			alert("已经是首页");
			return;
		}else{
			currentPage=currentPage-1;
		}
	}
	if(flag=="next")
	{
		if(totalPage==currentPage)
		{
			alert("已经是末页");
			return;
		}else{
			currentPage=currentPage+1;
		}
	}
	
	if(flag=="last")
	{
		currentPage=totalPage;
	}
	
	if(flag=="absolute")
	{
		//获得用户输入的页数
		var absPage=document.all("pageNo").value;
		//判断输入的页码是有效的数字
		if(isNaN(absPage))
		{
			alert("请输入有效的数字");
			return;
		}else{
			var temp_page=parseInt(absPage);
			//判断是否是有效的范围
			if(!(temp_page>0&&temp_page<=totalPage))
			{
				alert("跳转超出了范围");
			}else{
				currentPage=absPage;
			}
		}
	}
	//发送请求
	document.location.href="selectMenuAll.action?currentPage="+currentPage+"&pageSize="+pageSize;
}
</SCRIPT>
	<body><form name="fo" action="menu.action" method="post">
		菜单名
		<input name="menu.menu_name" type="text" class="sidebarHeader" size="30" />	
		<s:submit value="搜索" method="selectMenu"></s:submit>
		<br />
		<span class="newfont07">选择：<a href="#" class="right-font08" onClick="selectAll();">全选</a>
		-<a href="#" class="right-font08" onClick="unselectAll();">反选</a></span>
		<s:submit value="删除所选" method="deleteMenuAll"></s:submit>
		<table width="953" height="87" bgcolor="#0099FF">
			<tr bgcolor="#3399FF">
				<td width="28" height="39" align="left">
			      <div align="left"></div></td>
				<td width="146" align="left"><div align="left">菜单号</div></td>
				<td width="176" align="left">
					
			      <div align="left">菜单名					</div></td>
				<td width="134" align="left">
					
			      <div align="left">根目录					</div></td>
				<td width="146" align="left">
					
			      <div align="left">url					</div></td>
				<td width="166" align="left">
					
			      <div align="left">菜单类型					</div></td><td></td>
			</tr>
			<s:iterator value="#request.menuinfo.lstResult" id="menuinfo">
													<tr bgcolor="#99CCFF" >
														<td height="40">
															<input type="checkbox" name="menuId" value="<s:property value="#menuinfo.menu_id"/>"/>													  </td>
														<td><div align="center">
														<s:property value="#menuinfo.menu_id"/></div></td>
														<td>
														<div align="center">
														<s:property value="#menuinfo.menu_name"/>													    </div></td>
														<td>
														<div align="center">
														<s:property value="#menuinfo.parent"/>												    </div></td>
														<td>
														<div align="center">
														<s:property value="#menuinfo.url"/>												    </div></td>
														<td>
														  <div align="center">
														  <s:property value="#menuinfo.menu_type"/>												    </div></td>
														<td width="125">
														  <div align="center"><a href="<%=request.getContextPath()%>/jsp/selectMenuId.action?menu.menu_id=<s:property value="#menuinfo.menu_id"/>">查看|</a>
														    <a href="<%=request.getContextPath()%>/jsp/selectMenuInfo.action?menu.menu_id=<s:property value="#menuinfo.menu_id"/>">编辑|</a>
														    <a href="<%=request.getContextPath()%>/jsp/deleteMenu.action?menu.menu_id=<s:property value="#menuinfo.menu_id"/>">删除</a> </div></td>
													</tr>
		  </s:iterator>
	  </table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="right-font08">
			<tr>
				<td width="37%">
					共
					<span class="right-text09">${requestScope.menuinfo.totalPage}</span>
					页 | 第
					<span class="right-text09">${requestScope.menuinfo.currentPage}</span>
					页				</td>
				<td width="39%" align="right" nowrap>
					[
					<a href="javascript:goToPage('first')" class="right-font08">首页</a>
					|
					<a href="javascript:goToPage('previous')" class="right-font08">上一页</a>
					|
					<a href="javascript:goToPage('next')" class="right-font08">下一页</a>
					|
					<a href="javascript:goToPage('last')" class="right-font08">末页</a>]
					转至：				</td>
				<td width="24%">
				<table width="20" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="1%">
					<input name="textfield3" type="text" class="sidebarHeader"
						size="1" />
				</td>
				<td width="87%">
					<input name="Submit23222" type="submit" class="titlebar"
						value="Goto" onClick="goToPage('absolute')"/>
				</td>
			</tr>
		</table>
			  </td>
			</tr>
		</table>
		<c:if test="${requestScope.menuinfo.totalPage==0}">
		<script language="javascript">
				alert("您查找的数据不存在!");
			</script>
		</c:if>
		</form>
	</body>
</html>

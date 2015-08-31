<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
	<SCRIPT type="text/javascript" language="javascript">
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "map"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "map"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
//地理位置

//当前页
var currentPage=parseInt("${requestScope.pageinfo.currentPage}");
//每页记录数
var pageSize=parseInt("${requestScope.pageinfo.pageSize}");
//总页数
var totalPage=parseInt("${requestScope.pageinfo.totalPage}");
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
	document.location.href="selectAll.action?currentPage="+currentPage+"&pageSize="+pageSize;
}
</SCRIPT>
	<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
	<style type="text/css">
<!--
.STYLE4 {font-size: 18px}
.STYLE5 {font-size: small}
-->
    </style>
	</head>

	<body>
	<form name="fom"  action="map.action" method="post">
	<table><tr><td>地理名</td><td>
		<input name="place_name" type="text" class="sidebarHeader" /></td>		
	  <td>
	  城市级别</td><td>
		<select name="ranks" size="1" class="sidebarHeader"  >
		      <option >0</option>	
		      <option >1</option>
		      <option >2</option>
		      <option >3</option>
		      <option >4</option>
		      <option>5</option>
	    </select></td><td>
		所属类型</td><td>
		<input name="type" type="text" class="sidebarHeader"  />
	  </td><td>
	  <s:submit value="搜索" name="selectinfo" method="select_map"/></td></tr></table>
		<a href="<%=request.getContextPath()%>/map/selectAll.action">显示全部</a>
	<span class="newfont07">选择：<a href="#" class="right-font08" onClick="selectAll()">全选</a>
		-<a href="#" class="right-font08" onClick="unselectAll()">反选</a></span>
		<s:submit value="删除所选" name="delete" method="deleteAll" />
		<table width="898" height="95" border="1" bordercolor="#CC99FF">
			<tr>
			  <td width="36" height="42" bgcolor="#99CCCC"><div align="left"><span class="STYLE4"></span> </div></td>
				<td width="83" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">地理名				</div>
			  </div></td>
				<td width="63" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">经度				</div>
			  </div></td>
				<td width="60" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">纬度				</div>
			  </div></td>
				<td width="83" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">位置类型				</div>
			  </div></td>
				<td width="101" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">上级地理名				</div>
			  </div></td>
				<td width="73" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">城市级别				</div>
			  </div></td>
				<td width="233" bgcolor="#99CCCC">
					
			  <div align="center" class="STYLE4">
			    <div align="left">详细地址				</div>
			  </div></td>
			  <td width="108" bgcolor="#99CCCC"><div align="left"><span class="STYLE4">操作</span></div></td>
			</tr>
			<s:iterator value="#request.pageinfo.lstResult" id="map_info">
													<tr bgcolor="#6699FF" >
														<td height="45" bordercolor="#9966FF">
													      <div align="left" class="STYLE5">
													        <input type="checkbox" name="map" 
															value="<s:property value="#map_info.map_id"/>"/>													  
											          </div></td>
														
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">
														  <s:property value="#map_info.place_name"/>
  														  </div></td>
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">
														  <s:property value="#map_info.map_longitude"/>														</div></td>
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">	
														  	<s:property value="#map_info.map_latitude"/>													</div></td>
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">
														  <s:property value="#map_info.type"/>															</div></td>
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">
														  <s:property value="#map_info.last_place"/>															</div></td>
                                                          <td bordercolor="#9966FF">
													        <div align="left" class="STYLE5">	
															<s:property value="#map_info.ranks"/>														</div></td>
														<td bordercolor="#9966FF">
													      <div align="left" class="STYLE5">	
														  <s:property value="#map_info.address"/>														</div></td>
														<td bordercolor="#9966FF">
															<div align="left" class="STYLE5"><a href="<%=request.getContextPath()%>/map/selectId.action?map_log_lat.map_id=<s:property value="#map_info.map_id"/>">编辑|</a>
													          <a href="<%=request.getContextPath()%>/map/deleteMap.action?map_log_lat.map_id=<s:property value="#map_info.map_id"/>">删除</a> </div></td>
			   </tr>
		  </s:iterator>
	  </table>
		<table width="100%" border="0" align="left" cellpadding="0"
			cellspacing="0" class="right-font08">
			<tr>
				<td width="50%">
					共
					<span class="right-text09">${requestScope.pageinfo.totalPage}</span>
					页 | 第
					<span class="right-text09">${requestScope.pageinfo.currentPage}</span>
					页
				</td>
				<td width="49%" align="right" nowrap>
					[
					<a href="javascript:goToPage('first')" class="right-font08">首页</a>
					|
					<a href="javascript:goToPage('previous')" class="right-font08">上一页</a>
					|
					<a href="javascript:goToPage('next')" class="right-font08">下一页</a>
					|
					<a href="javascript:goToPage('last')" class="right-font08">末页</a>]
					转至：				</td>
				<td width="1%">
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
		<c:if test="${requestScope.delete!=null}">
			<script language="javascript">
				alert("${requestScope.delete}");
			</script>
		</c:if>
		<c:if test="${requestScope.pageinfo.totalPage==0}">
			<script language="javascript">
				alert("您查找的数据不存在!");
			</script>
		</c:if>
		
	</form>
	</body>
</html>

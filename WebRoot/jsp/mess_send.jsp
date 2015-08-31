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
		if (obj[i].name == "mesID"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fo.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "mesID"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
//当前页
var currentPage=parseInt("${requestScope.mesinfo.currentPage}");
//每页记录数
var pageSize=parseInt("${requestScope.mesinfo.pageSize}");
//总页数
var totalPage=parseInt("${requestScope.mesinfo.totalPage}");
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
	document.location.href="selectMesSend.action?currentPage="+currentPage+"&pageSize="+pageSize;
}
</SCRIPT>
	<body><form name="fo" action="deletemes.action" method="post">
		<span class="newfont07">选择：<a href="#" class="right-font08" onClick="selectAll();">全选</a>
		-<a href="#" class="right-font08" onClick="unselectAll();">反选</a></span>
		<s:submit value="删除所选" ></s:submit>
		<table width="856" height="46" border="2" bordercolor="#9966FF" bgcolor="#FFCCFF">
		<s:iterator value="#request.mesinfo.lstResult" id="mesinfo">
													<tr >
														<td width="44" height="40">
															<input type="checkbox" name="mesID" value="<s:property value="#mesinfo.mes_id"/>"/>
													  </td>
														<td width="89">
														<s:property value="#mesinfo.send"/>
													  </td>
														<td width="775">
														<a href="
														<%=request.getContextPath()%>/jsp/selectmesinfo.action?message.mes_id=<s:property value="#mesinfo.mes_id"/>">	
													  <s:property value="#mesinfo.title"/></a>														</td>
			   </tr>
		  </s:iterator>
	  </table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="right-font08">
			<tr>
				<td width="38%">
					共
					<span class="right-text09">${requestScope.mesinfo.totalPage}</span>
					页 | 第
					<span class="right-text09">${requestScope.mesinfo.currentPage}</span>
					页				</td>
				<td width="47%" align="right" nowrap>
					[
					<a href="javascript:goToPage('first')" class="right-font08">首页</a>
					|
					<a href="javascript:goToPage('previous')" class="right-font08">上一页</a>
					|
					<a href="javascript:goToPage('next')" class="right-font08">下一页</a>
					|
					<a href="javascript:goToPage('last')" class="right-font08">末页</a>]
					转至：				</td>
				<td width="15%">
				<table width="20" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="1%"><input name="textfield3" type="text" class="sidebarHeader"
						size="1" /></td>
				<td width="87%">
					<input name="Submit23222" type="submit" class="titlebar"
						value="Goto" onClick="goToPage('absolute')"/>				</td>
			</tr>
		</table>
			  </td>
			</tr>
		</table>
		<c:if test="${requestScope.mesinfo.totalPage==0}">
		<script language="javascript">
				alert("您查找的数据不存在!");
			</script>
		</c:if>
		<c:if test="${requestScope.error!=null}">
		<script language="javascript">
				alert("${requestScope.error}");
			</script></c:if>
		</form>
	</body>
</html>
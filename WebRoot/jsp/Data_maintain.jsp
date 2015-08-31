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

//当前页
var currentPage=parseInt("${requestScope.dateinfo.currentPage}");
//每页记录数
var pageSize=parseInt("${requestScope.dateinfo.pageSize}");
//总页数
var totalPage=parseInt("${requestScope.dateinfo.totalPage}");
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
	document.location.href="selectDateAll.action?currentPage="+currentPage+"&pageSize="+pageSize;
}
</SCRIPT>
	<body><form name="fom" action="selectdate.action" method="post">
		表名<select name="dateMaintain.table_name" class="sidebarHeader">
<s:iterator value="#request.table"id="table" ><option><s:property value="#table.Tables_in_digger"/></option></s:iterator>
</select>
<input type="submit" class="titlebar" value="搜索"/>
<table width="686" height="88" border="1" bordercolor="#CC99FF" bgcolor="#99CCFF">
			<tr bgcolor="#66CCCC">
				<td width="131" height="33">
					
			      <div align="left">备份名					</div></td>
				<td width="121">
					
			      <div align="left">表名					</div></td>
				<td width="163">
					
			      <div align="left">备份时间					</div></td>
				  <td><div align="left">操作</div></td>
			</tr>
			<s:iterator value="#request.dateinfo.lstResult" id="dateinfo">
			<tr bgcolor="#99CCFF"><td>
			  <div align="left">
			    <s:property value="#dateinfo.date_name"/>
		      </div></td>
			<td>
			  <div align="left">
			    <s:property value="#dateinfo.table_name"/>
		      </div></td>
			<td>
			  <div align="left">
			    <s:property value="#dateinfo.date_time"/>
		      </div></td>
			<td width="251"> <div align="left"><a href="<%=request.getContextPath()%>/jsp/recovery.action?dateMaintain.date_path=
			<s:property value="#dateinfo.date_path"/> &dateMaintain.table_name=
			  <s:property value="#dateinfo.table_name"/>
  ">恢复备份|</a>
			  <a href="<%=request.getContextPath()%>/digger/<s:property value="#dateinfo.date_name"/>">下载备份</a>
			  </div></td>
			</tr>
			</s:iterator>
	  </table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="right-font08">
			<tr align="left">
				<td width="34%" align="left">
					共
					  <span class="right-text09">${requestScope.dateinfo.totalPage}</span>
				  页 | 第
					<span class="right-text09">${requestScope.dateinfo.currentPage}</span>
				  页				</td>
				<td width="29%" align="right">[
					<a href="javascript:goToPage('first')" class="right-font08">首页</a>
					|
					<a href="javascript:goToPage('previous')" class="right-font08">上一页</a>
					|
					<a href="javascript:goToPage('next')" class="right-font08">下一页</a>
					|
					<a href="javascript:goToPage('last')" class="right-font08">末页</a>]
					转至：				</td>
				<td width="37%" align="left">			      <table width="20" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="1%">
			          <input name="textfield3" type="text" class="sidebarHeader"
						size="1" />				          </td>
				  <td width="87%">
				    <input name="Submit23222" type="submit" class="titlebar"
						value="Goto" onClick="goToPage('absolute')"/>				    </td>
			  </tr>
                        </table></td>
			</tr>
	  </table>
		<c:if test="${requestScope.dateinfo.totalPage==0}">
		<script language="javascript">
				alert("您查找的数据不存在!");
			</script>
		</c:if>
		<c:if test="${sessionScope.flag==false}">
		<script language="javascript">
				alert("备份失败，可能原因书与数据表中数据冲突！");
			</script>
		</c:if>
		</form>
	</body>
</html>

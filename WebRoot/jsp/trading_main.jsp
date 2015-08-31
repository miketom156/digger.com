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
var currentPage=parseInt("${requestScope.tradinginfo.currentPage}");
//每页记录数
var pageSize=parseInt("${requestScope.tradinginfo.pageSize}");
//总页数
var totalPage=parseInt("${requestScope.tradinginfo.totalPage}");
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
	document.location.href="selectTradingAll.action?currentPage="+currentPage+"&pageSize="+pageSize;
}
</SCRIPT>
	<body><form name="fo" action="selecttrading.action" method="post">
		交易情况
		<select name="isnotPay" class="sidebarHeader">
		<option>是</option><option>否</option>
	  </select>	
		<s:submit value="搜索" ></s:submit>
		<br />
		<table width="884" height="79" border="2" bordercolor="#9900FF" bgcolor="#99CCFF">
			<tr>
				<td width="11" height="39">
			  <div align="center"></div>			  </td>
				<td width="141"><div align="center">卖家</div></td>
				<td width="153">
					<div align="center">
			  金额					</div>			  </td>
				<td width="74">
					<div align="center">
			  是否支付					</div>			  </td>
				<td width="182">
					<div align="center">
			  交易内容					</div>			  </td>
				<td width="222">
					<div align="center">
			  交易时间					</div>			  </td><td></td>
			</tr>
			 <c:forEach var="tradinginfo" items="${requestScope.tradinginfo.lstResult}">
													<tr bgcolor="#00CC00" >
														<td height="20">														</td>
														<td>${tradinginfo.sellers}</td>
														<td>
													  ${tradinginfo.money}												        </td>
														<td>
														<c:if test="${tradinginfo.isN_pay==0}">
														  否</c:if>
														<c:if test="${tradinginfo.isN_pay==1}">
												      是</c:if>														</td>
														<td>
													  ${tradinginfo.trading_info}													    </td>
														<td>
													  ${tradinginfo.trading_time}													    </td>
														<td width="53"><c:if test="${tradinginfo.isN_pay==0}">
												      <a href="<%=request.getContextPath()%>/jsp/trading.action?trading.pay_id=${tradinginfo.pay_id}">支付</a></c:if>												</td>
			   </tr>
		  </c:forEach>
	  </table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="right-font08">
			<tr>
				<td width="51%">
					共
					<span class="right-text09">${requestScope.tradinginfo.totalPage}</span>
					页 | 第
					<span class="right-text09">${requestScope.tradinginfo.currentPage}</span>
					页				</td>
				<td width="36%" align="right" nowrap>
					[
					<a href="javascript:goToPage('first')" class="right-font08">首页</a>
					|
					<a href="javascript:goToPage('previous')" class="right-font08">上一页</a>
					|
					<a href="javascript:goToPage('next')" class="right-font08">下一页</a>
					|
					<a href="javascript:goToPage('last')" class="right-font08">末页</a>]
					转至：				</td>
				<td width="13%">
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
		<c:if test="{requestScope.tradinginfo.totalPage==0}">
		<script language="javascript">
				alert("您查找的数据不存在!");
			</script>
		</c:if>
		</form>
	</body>
</html>

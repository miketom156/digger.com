<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=GB2312"/>
    <title>Google Maps JavaScript API范例1：基本范例</title>
    <script charset="utf-8" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAyXSTHPgwLMZ5rCgp9idmjhS0hBfgIoB8COnZtBjYLAnAeoqp1BQTbs1HyWOH7wOqHryBkaXNQ1DC4Q"
            type="text/javascript"></script>
    <script type="text/javascript">
    //<![CDATA[

    function load() {

var map = new GMap2(document.getElementById("map"));
map.addControl(new GSmallMapControl());
map.addControl(new GMapTypeControl());
map.setCenter(new GLatLng(39.88174,116.40632),4);


  var point = new GLatLng(22.9,112.0);
  map.addOverlay(new GMarker(point));


}
    //]]>
    </script>
  </head>
  <body onload="load()" onunload="GUnload()">
		<form action="map.action" method="post">
			<table width="922" height="560">
				<tr>
					<td>
						<table width="206" height="545">
							<tr>
								<td height="72">
								<div>
								<input type="text" size="20"name="map_log_lat.place_name"/>
								<s:submit value="搜索" method="selectMap"/>
								<s:fielderror name="!"></s:fielderror>
								</div>
							  </td>
							</tr>
							<tr>
								<td height="63">
									<div>
										<s:if test="#request.mapaaa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#request.mapaaa.ranks"/>&map_log_lat.place_name=<s:property value="#request.mapaaa.place_name"/>&map_log_lat.type=nothing">${requestScope.mapaaa.place_name}</a>>></s:if>
										<s:if test="#request.mapaa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#request.mapaa.ranks"/>&map_log_lat.place_name=<s:property value="#request.mapaa.place_name"/>&map_log_lat.type=nothing">${requestScope.mapaa.place_name}</a>>></s:if>
										<s:if test="#request.mapa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#request.mapa.ranks"/>&map_log_lat.place_name=<s:property value="#request.mapa.place_name"/>&map_log_lat.type=nothing">${requestScope.mapa.place_name}</a>>></s:if>
										<s:if test="#request.map.place_name!=null&&#request.map.ranks<5">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#request.map.ranks"/>&map_log_lat.place_name=<s:property value="#request.map.place_name"/>&map_log_lat.type=nothing">${requestScope.map.place_name}</a>
										</s:if>
									</div>
								</td>
							</tr>
							<tr>
								<td height="112">
									<div>
										<s:iterator value="#request.Map_info" id="Map_info">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#Map_info.ranks"/>&map_log_lat.place_name=<s:property value="#Map_info.place_name"/>&map_log_lat.type=nothing">
												<s:property value="#Map_info.place_name" /> </a>|</s:iterator>
									</div>
								</td>
							</tr>
							<tr>
								<td height="51">
									<div>
										<a
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=电影院&map_log_lat.place_name=<s:property value="#request.map.place_name"/>&map_log_lat.ranks=<s:property value="#request.map.ranks"/>">电影院</a>|
										<a
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=医院&map_log_lat.place_name=<s:property value="#request.map.place_name"/>&map_log_lat.ranks=<s:property value="#request.map.ranks"/>">医院</a>|
										<a
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=学校&map_log_lat.place_name=<s:property value="#request.map.place_name"/>&map_log_lat.ranks=<s:property value="#request.map.ranks"/>">学校</a>
									</div>
								</td>
							</tr>
							<tr>
								<td height="174">
									<div>
										<s:iterator value="#request.maps" id="maps">
											<li><a href=""> <s:property value="#maps.address" />
											</a></li></s:iterator>
									</div>
								</td>
							</tr>
					  </table>

					</td>
					<td>
						<div id="map" style="width: 900px; height: 550px"></div>
					</td>
				</tr>
			</table>
			</form>
	</body>
</html>
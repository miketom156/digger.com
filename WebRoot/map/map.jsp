<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.digger.model.Map_log_lat"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=GB2312"/>
    <title></title>
    <script charset="utf-8" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAyXSTHPgwLMZ5rCgp9idmjhS0hBfgIoB8COnZtBjYLAnAeoqp1BQTbs1HyWOH7wOqHryBkaXNQ1DC4Q"
            type="text/javascript"></script>
    <script type="text/javascript">
    //<![CDATA[
<%
@SuppressWarnings("unchecked")
ArrayList<Map_log_lat> maps=(ArrayList<Map_log_lat>) session.getAttribute("maps");
%>

    function load() {

var map = new GMap2(document.getElementById("map"));
map.addControl(new GSmallMapControl());
map.addControl(new GMapTypeControl());
map.setCenter(new GLatLng(${sessionScope.location.map_latitude},${sessionScope.location.map_longitude}),12);

//创建一个图标模板，指定阴影等
var baseIcon = new GIcon();
baseIcon.shadow = "http://www.google.com/mapfiles/shadow50.png";
baseIcon.iconSize = new GSize(20, 34);
baseIcon.shadowSize = new GSize(37, 34);
baseIcon.iconAnchor = new GPoint(9, 34);
baseIcon.infoWindowAnchor = new GPoint(9, 2);
baseIcon.infoShadowAnchor = new GPoint(18, 25);

//创建用指定字母作为前景图的图标
function createMarker(point, index) {
  // Create a lettered icon for this point using our icon class
  var letter = String.fromCharCode("A".charCodeAt(0) + (index-1));
  var icon = new GIcon(baseIcon);
  icon.image = "http://www.google.com/mapfiles/marker" + letter + ".png";
  var marker = new GMarker(point, icon);

<% for (int j=0;j<maps.size();j++){ %>
if(index==<%=j+1%>){
  GEvent.addListener(marker, "click", function() {
    marker.openInfoWindowHtml("Marker <b>"+letter+"<%=maps.get(j).getPlace_name()%>"+">>>>"+"<%=maps.get(j).getAddress()%>"+ "</b>");
  });
  return marker;
  }
<%}%>

}
<% for (int i=0;i<maps.size();i++){%>

var points =new GLatLng(<%=maps.get(i).getMap_latitude()%>,<%=maps.get(i).getMap_longitude()%>);
map.addOverlay(createMarker(points, <%=i+1%>));

<%}%>
}


    //]]>
    </script>
    <link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css">
  </head>
  <body onLoad="load()" onUnload="GUnload()">
		<form action="selectMap.action" method="post">
		<s:property value="#session.maperror"/>
			<table width="922" height="560">
				<tr>
					<td>
						<table width="206" height="545">
							<tr>
								<td height="72">
								<div>
								<input name="map_log_lat.address" type="text" class="sidebarHeader" size="20"/>
								<s:submit value="搜索" />
								<s:fielderror name="!"></s:fielderror>
								</div>
							  </td>
							</tr>
							<tr>
								<td height="63">
									<div>
										<s:if test="#session.mapaaa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#session.mapaaa.ranks"/>&map_log_lat.place_name=<s:property value="#session.mapaaa.place_name"/>&map_log_lat.type=nothing">${sessionScope.mapaaa.place_name}</a>>></s:if>
										<s:if test="#session.mapaa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#session.mapaa.ranks"/>&map_log_lat.place_name=<s:property value="#session.mapaa.place_name"/>&map_log_lat.type=nothing">${sessionScope.mapaa.place_name}</a>>></s:if>
										<s:if test="#session.mapa.place_name!=null">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#session.mapa.ranks"/>&map_log_lat.place_name=<s:property value="#session.mapa.place_name"/>&map_log_lat.type=nothing">${sessionScope.mapa.place_name}</a>>></s:if>
										<s:if test="#session.map.place_name!=null&&#session.map.ranks<5">
											<a
												href="<%=request.getContextPath()%>/map/select.action?map_log_lat.ranks=<s:property value="#session.map.ranks"/>&map_log_lat.place_name=<s:property value="#session.map.place_name"/>&map_log_lat.type=nothing">${sessionScope.map.place_name}</a>
										</s:if>
									</div>
								</td>
							</tr>
							<tr>
								<td height="112">
									<div>
										<s:iterator value="#session.Map_info" id="Map_info">
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
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=电影院&map_log_lat.place_name=<s:property value="#session.location.place_name"/>&map_log_lat.ranks=<s:property value="#session.location.ranks"/>">电影院</a>|
										<a
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=学校&map_log_lat.place_name=<s:property value="#session.location.place_name"/>&map_log_lat.ranks=<s:property value="#session.location.ranks"/>">学校</a>|
										<a
											href="<%=request.getContextPath()%>/map/select.action?map_log_lat.type=医院&map_log_lat.place_name=<s:property value="#session.location.place_name"/>&map_log_lat.ranks=<s:property value="#session.location.ranks"/>">医院</a>
									</div>
								</td>
							</tr>
							<tr>
								<td height="174">
									<div>
									地理位置名称》》》》详细地址
										<s:iterator value="#session.maps" id="maps">
										<li><s:property value="#maps.place_name" /> 》》》
										<s:property value="#maps.address" />
											</li></s:iterator>
									</div>
								</td>
							</tr>
					  </table>

					</td>
					<td>&nbsp;
						<div id="map" style="width: 900px; height: 550px"></div>
					</td>
				</tr>
			</table>
			</form>
	</body>
</html>
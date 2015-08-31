// JavaScript Document
 function load() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map"));
		map.addControl(new GSmallMapControl());
        map.setCenter(new GLatLng(33,111),11);
      }
    }

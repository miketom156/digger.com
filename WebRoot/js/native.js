function L1(name, lv2) {
	this.name = name;
	this.lv2 = lv2;
}
function L2(name, value) {
	this.name = name;
	this.value = value;
}
var t1=new Array(
		new L1("湖南省",new Array(
				new L2("长沙市",1),
				new L2("湘潭市",2),
				new L2("株洲市",3),
				new L2("岳阳市",4),
				new L2("常德市",5))),
		new L1("湖北省",new Array(
				new L2("武汉市",6),
				new L2("黄石市",7),
				new L2("十堰市",8),
				new L2("宜昌市",9),
				new L2("襄樊市",10))),
		new L1("海口省",new Array(
				new L2("海口市",11),
				new L2("三亚市",12),
				new L2("五指山市",13),
				new L2("琼海市",14),
				new L2("文昌市",15))));
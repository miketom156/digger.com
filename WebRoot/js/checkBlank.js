
function checkBlank(txtName){
var value=txtName.value;
var name=txtName.name;
var span;

if(value==""){
if(name=="user_name"){
 span=document.getElementById("span_1");
span.innerHTML="用户名不能为空！";
}
else if(name="user_password"){
span=document.getElementById("span_2");
span.innerHTML="密码不能为空 ";
}
}
else
{
if(name=="user_name"){
 span=document.getElementById("span_1");
span.innerHTML="";
}
else if(name="user_password"){
span=document.getElementById("span_2");
span.innerHTML="";
}
}
	
}


function check(){
	var pass=document.getElementById("password").value;
	var pass1=document.getElementById("new_password").value;
	var span;
	if(pass!=pass1){
		span=document.getElementById("span_3");
		span.innerHTML="确认密码和密码不一致！ ";}
	else{
		span=document.getElementById("span_3");
		span.innerHTML="";}
	}

function HQDate()
{
    var tYEAR=document.getElementById('tYEAR').value;
    var tmon=document.getElementById('tMON').value;
    var tday=document.getElementById('tDAY').value;
    var my=tYEAR+"-"+tmon+"-"+tday;
    var span;
    my = my.replace(/-/g,"/");//替换字符，变成标准格式
var d2=new Date();//取今天的日期
var d1 = new Date(Date.parse(my));
//alert(d1);
//alert(d2);
if(d1>d2){
	span=document.getElementById("span_4");
	span.innerHTML="此生日不存在 ";
	}else
	{
		span=document.getElementById("span_4");
		span.innerHTML=" ";
	}
}

function checkDate(){
	var date=document.getElementById('date').value;
	var d2=new Date();
	var d1=new Date(Date.parse(date));
	if(d1>d2){
		span=document.getElementById("span_4");
		span.innerHTML="此生日不存在 ";
		}else
		{
			span=document.getElementById("span_4");
			span.innerHTML=" ";
		}
}


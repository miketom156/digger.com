
function showDate(u){  
var year=u.getFullYear();  
var month=u.getMonth()-1;  
var day=u.getDate()-1;  

var $=document.getElementById;  
$("tYEAR").options.selectedIndex=2008-year;  
$("tMON").options.selectedIndex=month;  
$("tDAY").options.selectedIndex=day;  

}  
  function createSelect(ActionFlag) {   
  var selYear = document.getElementById("tYEAR");   
  var selMonth = document.getElementById("tMON");   
  var selDay = document.getElementById("tDAY");   
  var dt = new Date();   

  if(ActionFlag == 1) {   
  MaxYear = dt.getFullYear();   
  MinYear = dt.getFullYear()-40;   

  for(var i = MaxYear; i >= MinYear; i--) {   
  var op = document.createElement("OPTION");   
  op.value = i;   
  op.innerHTML = i;   
  selYear.appendChild(op);   
  }   

  for(var i = 1; i < 13; i++) {   
  var op = document.createElement("OPTION");   
  op.value = i;   
  op.innerHTML = i;   
  selMonth.appendChild(op);   
  }   
    
  }   

  var date = new Date(selYear.value, selMonth.value, 0);   
  var daysInMonth = date.getDate();   
  selDay.options.length = 0;   

  for(var i = 1; i <= daysInMonth ; i++) {   
  var op = document.createElement("OPTION");   
  op.value = i;   
  op.innerHTML = i;   
  selDay.appendChild(op);   
  }   
    
  } 

var curDt = new Date();
	        
function disablementFunction(day){		        
	var selectedDay = day.date;
    if (day.isWeekend) return false;
    if (curDt==undefined){
        curDt = selectedDay.getDate;
    }	
    return (getDaysBetweenDates(curDt, selectedDay) >= 0) ? true : false;  
}

function disabledClassesProv(day){	        	
	var res = '';
    if (curDt.getTime() - day.date.getTime() > 0) res+='rich-calendar-boundary-dates ';	          
    if (day.isWeekend) res+='rich-calendar-boundary-dates ';		     
    return res;
}

function getDaysBetweenDates(d0, d1) {
	  var msPerDay = 8.64e7;
	  var x0 = new Date(d0);
	  var x1 = new Date(d1);
	  x0.setHours(9,30,0);
	  x1.setHours(9,30,0);
  	return Math.round( (x1 - x0) / msPerDay );
}
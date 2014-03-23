var curDt = new Date();
function disablementFunction(day){
    if (day.isWeekend) return false;
    if (curDt==undefined){
        curDt = day.date.getDate;
    }
    if (curDt.getTime() - day.date.getTime() > 0) return true; else return false;  
}
function disabledClassesProv(day){
	var res = ''; 
    if (curDt.getTime() - day.date.getTime() > 0) res+='rich-calendar-boundary-dates ';
    if (day.isWeekend) res+='rich-calendar-boundary-dates ';
    return res;
}

function setUserToLookUpTable(e){
	var control = $((e.target ? e.target : e.srcElement).id);
	if(control.createTextRange){
		var range = control.createTextRange();
		range.collapse(false);
		range.select();
	} else if(control.setSelectionRange){
		control.focus();
		var length = control.value.length;
		control.setSelectionRange(length,length);
	}
	control.selectionStart = control.selectionEnd = control.value.length;
}
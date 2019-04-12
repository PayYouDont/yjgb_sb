//把时间戳转换为年月日的日期格式
function formatLongDate(longDate){     
	var date = new Date(longDate);
	var Y = date.getFullYear();
	var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
	var D = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds(); 
	var time =Y+"/"+M+"/"+D+"/ "+h+":"+m+":"+s;
	return time;
}


//获取两个longtime时间  求时间差
function getLongDateDiffer(date3){
	var retValue = {};  
	 //计算出相差天数  
    var days = Math.floor(date3 / (24 * 3600 * 1000));  
    retValue.Days = days;  
  
    var years = Math.floor(days / 365);  
    retValue.Years = years;  
  
    var months = Math.floor(days / 30);  
    retValue.Months = months;  
  
    //计算出小时数  
    var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数  
    var hours = Math.floor(leave1 / (3600 * 1000));  
    retValue.Hours = hours;  
  
    //计算相差分钟数  
    var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数  
    var minutes = Math.floor(leave2 / (60 * 1000));  
    retValue.Minutes = minutes;  
  
    //计算相差秒数  
    var leave3 = leave2 % (60 * 1000);      //计算分钟数后剩余的毫秒数  
    var seconds = Math.round(leave3 / 1000);  
    retValue.Seconds = seconds;  
    console.log();
    return retValue; 
}

Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}
//倒计时功能
//FUNCTION MYCLOCK(LONGDATE){
//	VAR MYCLOCK=SETINTERVAL(
//		FUNCTION(){ 
//			VAR NOWTIME=NEW DATE();
//			VAR DATE3=LONGDATE-NOWTIME.GETTIME();
//			IF(DATE3>0){
//				VAR DIFFERTIME=GETLONGDATEDIFFER(DATE3);
//				CONSOLE.LOG(DIFFERTIME);
//				VAR HTML=DIFFERTIME.YEARS+"年"+DIFFERTIME.MONTHS+"月"+DIFFERTIME.DAYS+"日  "
//						+DIFFERTIME.HOURS+":"+DIFFERTIME.MINUTES+":"+DIFFERTIME.SECONDS;
//				$("#CLOCK").HTML(HTML);
//			}ELSE{
//				ALERT();
//				$("#CLOCK").HTML("0年0月0日  00:00:00");
//				CLEARINTERVAL(MYCLOCK);
//			}
//		
//		}
//		
// ,1000);
//	
//}



//1必须是数字，下划线  字母的任意组合
$.extend($.fn.validatebox.defaults.rules, {   
    code: {    
        validator: function(value,param){
        	var reg=/^[0-9a-zA-Z_]{1,}$/;
        	if(reg.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: '请输入数字 ，字母下划线的任意组合'   
    }    
});




//2数字类型(可以有小数点，负数)
$.extend($.fn.validatebox.defaults.rules, {   
    number: {    
        validator: function(value,param){
        	var reg=/^(?:0|\-?(?:0\.\d*[1-9]|[1-9]\d*(?:\.\d*[1-9])?))$/;
        	if(reg.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: '请输入数字类型 '   
    }    
});





//3有效电话验证
$.extend($.fn.validatebox.defaults.rules, {    
    phoneNum: {    
        validator: function(value,param){
        	var re1=/^1\d{10}$/;
        	var re2 = /^0\d{2,3}-?\d{7,8}$/;  
        	if(re1.test(value) ||re2.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: '请输入有效电话'   
    }    
});


//4  1到100 的整数数字
$.extend($.fn.validatebox.defaults.rules, {   
    oneToHundred: {    
        validator: function(value,param){
        	var reg=/^(([1-9]\d?)|100)$/;
        	if(reg.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: '请输入1到100的任意正数数字 '   
    }    
});


//5ip格式
$.extend($.fn.validatebox.defaults.rules, {   
    IP: {    
        validator: function(value,param){
        	var reg=/^(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])\.(\d|[1-9]\d|1\d{2}|2[0-5][0-5])$/;
        	if(reg.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: 'IP(例如：192.168.3.155)'   
    }    
});


//6端口格式
$.extend($.fn.validatebox.defaults.rules, {   
    Port: {    
        validator: function(value,param){
        	var reg=/^([0-9]|[1-9]\d{1,3}|[1-5]\d{4}|6[0-5]{2}[0-3][0-5])$/;
        	if(reg.test(value)){
        		 return true;
        	}else{
        		return false;
        	}
        },    
        message: '端口格式(例如：8080)'   
    }    
});







//密码一致性验证 
$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '密码不一致！'   
    }    
}); 



//密码格式验证
$.extend($.fn.validatebox.defaults.rules, {    
    pass: {    
        validator: function(value,param){
        	var reg = /^[\w]{6,18}$/;  
        	return reg.test(value);
        },    
        message: '密码只能是6到18位的数字 ，字母和下划线的任意组合!'   
    }    
});

//下拉框必选
//<select data-options="validType:'custom_nameForAdd[\'#deviceTypeId\']'" 
//	class="easyui-combobox easyui-validatebox" 
//	id="deviceTypeId"
//</select> 
$.extend($.fn.validatebox.defaults.rules, {
	selectValueRequired: {
        validator: function(value, param) {
           // console.info($(param[0]).find("option:contains('"+value+"')").val());
            return $(param[0]).find("option:contains('"+value+"')").val() != '';  
        },
        message: "该项是必选项！"
    }
});


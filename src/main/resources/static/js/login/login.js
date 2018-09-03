
//清除表单
function myClearForm(){
	$("#dataForm1").form('clear');
	$("#dataForm2").form('clear');
}



//播发系统登录
function login1(){
	$.messager.progress({title:'系统提示',text:'正在登录...',interval:300});
	$('#dataForm1').form('submit',{
		url:'../userAction/login',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){$.messager.progress('close');}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			data = JSON.parse(data);
			if (data.success){
				window.location.href="../emergencyInfoAction/index";
			}else{
				$("font[class='tooltipFont']").html(data.data);
			}
		}
	});
}



//播发系统登录
function login2(){
	$.messager.progress({title:'系统提示',text:'正在登录...',interval:300});
	$('#dataForm2').form('submit',{
		url:'../userAction/login',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){$.messager.progress('close');}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			data = JSON.parse(data);
			if (data.success){
				window.location.href="../menuAction/index";
			}else{
				$("font[class='tooltipFont']").html(data.data);
			}
		}
	});
}


//登录应急广播监控平台
function login3(){
	$.messager.progress({title:'系统提示',text:'正在登录...',interval:300});
	$('#dataForm3').form('submit',{
		url:'../userAction/login',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){$.messager.progress('close');}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			data = JSON.parse(data);
			if (data.success){
				window.location.href="../monitorAction/index";
			}else {
				$("font[class='tooltipFont']").html(data.data);
			}
		}
	});
}





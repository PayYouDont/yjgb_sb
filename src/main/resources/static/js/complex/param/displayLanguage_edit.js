//保存事件类型记录
function mysave(){
	//验证事件名称
	$("#edit_form").form('submit',{
		url:"../displayLanguageAction/save",
		onSubmit:function(){
			var isValid = $(this).form("validate");
			return isValid;
		},
		success:function(data){
			var json = JSON.parse(data);
			if(json.success){
				$.messager.alert("系统提示","播发语言保存成功","info",function(){
					closeParentModal();
					refreshParentData();
				});
		    }else{
		    	$.messager.alert("系统提示",json.data,"error");
		    }
			
		},
		error:function(data){
			$.messager.alert("系统提示","服务器内部错误","info");
		}
		
	});
    
}


function closeParentModal(){
	window.parent.closeMyModal();
}


function refreshParentData(){
	window.parent.refreshMyData();
}


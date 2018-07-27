function closeParentModal(){
	window.parent.closeMyModal();
}


//保存事件类型记录
function mysave(){
	 $.messager.progress({
	       title : '系统提示',
	       text : '信息提交，请稍后。。。',
	       interval:300
	 });
	//验证事件名称
	$("#edit_form").form('submit',{
		url:"../accidentTypeAction/save",
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success:function(data){
			var json = JSON.parse(data);
			$.messager.progress('close');	
			if(json.success){
				$.messager.alert("系统提示","事件类型保存成功","info",function(){
					window.parent.closeMyModal();
					window.parent.refreshMyPage();
				});
		    }else{
		    	$.messager.alert("系统提示",json.data,"error");
		    }
		},
		error:function(data){
			$.messager.alert("系统提示","服务器错误","error");
		}
	});
}





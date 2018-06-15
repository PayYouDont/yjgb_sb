$(document).ready(function(){
	$('#userAreCode').combotree({    
	    url:'../administrativeAction/getTreeJsonBySystem2', 
	    required: true,
	    panelWidth:200,
	    missingMessage:"该项必选",
	    onLoadSuccess:function(){
	    	
	    }
	}); 
});





//重置表单
function clearForm(){
	$('#dataForm').form('clear');
}


//保存
function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../userAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(json){
			data = JSON.parse(json);
			$.messager.progress('close');	
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshMyData();
				});
			}else{
				$.messager.alert('异常', '保存失败!','error');
			}
		}
	});
}


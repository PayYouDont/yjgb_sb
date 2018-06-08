
//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}





 function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交中，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../nodeAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(json){
			data = JSON.parse(json);
			$.messager.progress('close');	// hide progress bar while submit successfully
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.progress('close');
				$.messager.alert('异常', '异常保存失败!','error');
			}
		}
	});
}
 




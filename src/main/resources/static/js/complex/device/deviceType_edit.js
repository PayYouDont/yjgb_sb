//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}



//保存设备类型 
function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../deviceTypeAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');	// hide progress bar while submit successfully
			data = JSON.parse(data);
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.alert('异常', data.data,'error');
			}
		}
	});
}

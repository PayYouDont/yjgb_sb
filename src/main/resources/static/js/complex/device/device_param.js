
//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}


//表单重置
 function clearForm(){
 	$('#dataForm').form('clear');
 }
 
 
 

//基本信息修改
function mysave(){
	 $.messager.progress({
      title : '系统提示',
      text : '信息提交中，请稍后。。。',
      interval:300
  });
	$('#dataForm').form('submit',{
		url:'../backCommunicationAction/devParamSetting',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');	// hide progress bar while submit successfully
			if (data=="ok"){
				$.messager.alert('系统提示', '修改成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.alert('异常', '修改失败!','error');
			}
		}
	});
}


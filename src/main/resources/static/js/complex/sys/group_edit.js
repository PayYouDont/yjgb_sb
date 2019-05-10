$(document).ready(function(){
	$('#pid').combotree({    
	    url:'../menuAction/list', 
	    required: true,
	    missingMessage:"改选必选"
	}); 
});




//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}

//刷新父级页面数据
 function refreshParentData(){
		window.parent.refreshMyData();
 }


 
 
 
//保存
function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../groupAction/save',
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
					closeParentModal();
					refreshParentData();
				});
			}else{
				$.messager.progress('close');	
				$.messager.alert('异常', '保存失败!','error');
			}
		}
	});
}

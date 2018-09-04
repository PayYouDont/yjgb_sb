
//清除表单
function myClearForm(){
	$("#dataForm1").form('clear');
	$("#dataForm2").form('clear');
}

//播发系统登录
function login(a){
	var $form = $(a).parent().prev();
	var isValid= $form.form('enableValidation').form('validate');
	if (!isValid){
		$.messager.progress('close')
		return;
	}
	var data = $form.serializeArray();
	$.ajax({
		url:'../userAction/login',
		type:"post",
		data:data,
		dataType:"json",
		success:function(json){
			$.messager.progress('close');
			if(json.success){
				var title = $form.attr("title");
				if(title.indexOf("播发")!=-1){
					window.location.href="../emergencyInfoAction/index";
				}else if(title.indexOf("管理")!=-1){
					window.location.href="../menuAction/index";
				}else if(title.indexOf("监控")!=-1){
					window.location.href="../monitorAction/index";
				}
			}else{
				$form.find(".tooltipFont").html(json.data);
			}
		}
	})
}
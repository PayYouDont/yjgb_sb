
$(function(){
	$(document).keydown(function(e) {
		if(e.keyCode==13){//回车
			keyDownLogin();
		}else if(e.keyCode==37){//方向键←
			var a = $(".tabs-inner");
			for (var i = 1; i < a.length; i++) {
				var li = $(a[i]).parent();
				if(li.hasClass("tabs-selected")){
					$(a[i-1]).click();
					return;
				}
			}
		}else if(e.keyCode==39){//方向键→
			var a = $(".tabs-inner");
			for (var i = 0; i < a.length-1; i++) {
				var li = $(a[i]).parent();
				if(li.hasClass("tabs-selected")){
					$(a[i+1]).click();
					return;
				}
			}
		}
	})
})
function keyDownLogin(){
	var divs = $(".panel-htop").not(".window");
	for (var i = 0; i < divs.length; i++) {
		var div = divs[i];
		if(!$(div).is(":hidden")){
			var a = $(div).find(".easyui-linkbutton");
			login(a)
		}
	}
}
//清除表单
function myClearForm(){
	$("#dataForm1").form('clear');
	$("#dataForm2").form('clear');
	$("#dataForm3").form('clear');
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
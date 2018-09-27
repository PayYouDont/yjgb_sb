/***
 * 首页menuHandler
 */
function menuHandler(item){
	if(item.name == "logout"){
		$.messager.confirm("提示","确定要退出吗？",function(result){
			if(result){
				window.location = "../userAction/logout";
			}
		});
	}else if(item.name == "edit"){
		$("#editInfo").window("open");
	}else if(item.name == "theme"){
		
	}else if(item.name == "password"){
		$("#editPassword").window("open");
	}
}

//打开修改用户密码的模态框
function openUserModal(){
	var v_userName=$("#myuername").html();
	$('#userModal').window('setTitle','用户信息——'+v_userName);
	$('#userModal').window('open');
}

//用户修改密码修改按钮，提交form表单
function updateUserPassword(){
	$("#updatePassForm").submit();	
}
function mysave(){
	$("#info_form").form('submit',{
		url:"../userAction/updateUser",
		onSubmit:function(){
			var isValid = $(this).form("validate");
			return isValid;
		},
		success:function(data){
			data = JSON.parse(data);
			console.log(data)
			if(data.success){
				$.messager.alert("系统提示","用户信息修改成功，请重新登录！","info",function(){
					window.location.href="../userAction/logout";
				});
			}else{
				$.messager.alert("系统提示",data.data);
			}
		},
		error:function(data){
			$.messager.alert("系统提示","服务器内部错误！","error");
		}
	});
}
function changePWD(){
	$("#password_form").form('submit',{
		url:'../userAction/resetPwd',
		onSubmit:function(){
			var isValid = $(this).form("validate");
			return isValid;
		},
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				$.messager.alert("系统提示","密码修改成功，请使用新密码重新登录！","info",function(){
					window.location.href="../userAction/logout";
				});
			}else{
				$.messager.alert("错误",data.data);
			}
		},
		error:function(data){
			$.messager.alert("系统提示","网络异常！","error");
		}
	})
}
//检查密码和重新输入密码是相同的。
$.extend($.fn.validatebox.defaults.rules, {
 equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '两次输入内容不一致'
 }
});
//菜单跳转页面
function goPage(a){
	var menuName=$(a).children().html();
	var url=$(a).attr("id");
	var icon=$(a).attr("iconCls");	
	if ($('#myEasyui-tabs').tabs('exists', menuName)){
		$('#myEasyui-tabs').tabs('select', menuName);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:calc(100% - 6px);height:calc(100% - 6px);padding:3px;"></iframe>';
		$('#myEasyui-tabs').tabs('add',{
			title:menuName,
			content:content,
			iconCls:icon,
			closable:true
		});
	}
}



//删除tab
function closeTab(){
	var tab = $('#myEasyui-tabs').tabs('getSelected');  // 获取选择的面板
	var index = $('#myEasyui-tabs').tabs('getTabIndex',tab);
	$('#myEasyui-tabs').tabs('close',index);
	$('#myEasyui-tabs').tabs('select',index-1);

}


//刷新tab
function reloadTab(){
	var currTab = $('#myEasyui-tabs').tabs('getSelected');
	var url = $(currTab.panel('options').content).attr("src");
	
	if(url == undefined){
		url = "../deviceInfoAction/goBroatcastMonitorPage";
	}
	
	$('#myEasyui-tabs').tabs('update', { 
		tab: currTab,
		options:{content:'<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:calc(100% - 6px);height:calc(100% - 6px);padding:3px;"></iframe>'}
	});

}

//首页
function goHome(){
	var menuName="实时监控";
	var url="../deviceInfoAction/goBroatcastMonitorPage";
	var icon="icon-area";	
	if ($('#myEasyui-tabs').tabs('exists', menuName)){
		$('#myEasyui-tabs').tabs('select', menuName);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:calc(100% - 6px);height:calc(100% - 6px);padding:3px;"></iframe>';
		$('#myEasyui-tabs').tabs('add',{
			title:menuName,
			content:content,
			iconCls:icon,
			closable:true
		});
	}
}

/**
 * 全局刷新tab
 * @param title 选项卡标签
 * @param refreshGridFunc 自定义的刷新方法
 */
function refreshTabData(title, refreshGridFunc){
	console.log("into top function..............");
	if ($("#myEasyui-tabs" ).tabs('exists', title)) {  
        $('#myEasyui-tabs').tabs('select' , title);  
        console.log("ready to go review function.......")
        typeof refreshGridFunc === 'function' && refreshGridFunc.call();  
    }
}




//获取unitsession
function getUnitMessage(){
	var unitjson=$("#unit_json").val();
	console.log(unitjson);
	return unitjson;
}

//改变子页面资源(主要是暴露给子页面js)
function mainIframeUrl(url){
	$("#mainIframe").attr("src",url);	
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


/***
 * 首页menuHandler
 */
function menuHandler(item){
	console.log(item);
	if(item.name == "exit"){
		$.messager.confirm("提示","确定要退出吗？",function(result){
			if(result){
				window.location = "../userAction/exit";
			}
		});
	}else if(item.name == "edit"){
		$("#editInfo").window("open");
	}else if(item.name == "theme"){
		
	}else if(item.name == "password"){
		$("#editPassword").window("open");
	}
}


/**
 * 关闭修改信息模态框
 */
function closeModal(){
	$("#editInfo").window("close");
}

function mysave(){
	$("#info_form").form('submit',{
		url:"../userAction/updateUserBaseInfo",
		onSubmit:function(){
			var isValid = $(this).form("validate");
			return isValid;
		},
		success:function(data){
			if(data == "ok"){
				$.messager.alert("系统提示","用户信息修改成功，请重新登录！","info",function(){
					window.location.href="../userAction/exit";
				});
			}else if(data == "NameExist"){
				$.messager.alert("系统提示","用户名已存在！","info");
			}else if(data == "SuperAdmin"){
				$.messager.alert("系统提示","超级管理员无法修改","info");
			}
		},
		error:function(data){
			$.messager.alert("系统提示","服务器内部错误！","error");
		}
	});
}



function changePWD(){
	$("#password_form").form('submit',{
		url:'../userAction/changePasswordForNormalUser',
		onSubmit:function(){
			var isValid = $(this).form("validate");
			return isValid;
		},
		success:function(data){
			if(data == "ok"){
				$.messager.alert("系统提示","密码修改成功，请使用新密码重新登录！","info",function(){
					window.location.href="../userAction/exit";
				});
			}else if(data == "pwdWrong"){
				$.messager.alert("系统提示","旧密码错误","info");
			}else{
				$.messager.alert("系统提示","修改失败","info");
			}
		},
		error:function(data){
			$.messager.alert("系统提示","服务器内部错误！","error");
		}
	})
}

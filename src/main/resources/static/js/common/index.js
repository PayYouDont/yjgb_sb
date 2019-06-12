var tag={};
$(function(){
	getNvaMenuDetail();
	
})
function getNvaMenuDetail(){
	if(nvaMenuType){
		$.ajax({
			url:"../nvaMenuAction/emerNvaDetail",
			type:"post",
			data:{nvaMenuType:nvaMenuType},
			dataType:"json",
			success:function(json){
				if(json.success){
					let menus = json.data;
					for(let i=0;i<menus.length;i++){
                        let menu = menus[i];
                        let id = menu.id;
                        let text = menu.text;
                        let menuCaptionEn = menu.menucaptionen;
                        let menuImage = menu.menuimage;
						$(".easyui-accordion").accordion("add",{
							title: text,
							id:id,
							iconCls:menuImage,
							name:menuCaptionEn,
							selected: i==0?true:false
						})
					}
                    tag.panel = $(".easyui-accordion").accordion('getPanel','播发管理');
					//获取第一个菜单子菜单
					getChildMenu(0,menus[0].id);
					//绑定选中事件
					$(".easyui-accordion").accordion({
						onSelect:function(title,index){
                            let id = $(this).accordion("getSelected")[0].id;
                            getChildMenu(index,id);
						}
					});
				}
			}
		});
	}
}
function setTag(){
    let total = tag.data.total;
    if(tag.panel&&total>=0){
        let title = '播发管理';
        if(total>0){
            title = title+'<span class="badge" style="margin-left: 10px">'+total+'</span>';
        }
        tag.panel.panel('setTitle',title);
        tag.panel.panel('refresh');
        let $span = $(tag.panel).find('span').eq(0);
        if($span[0]){
            title = '信息管理';
            if(total>0){
                title = title + '<span class="badge" style="margin-left: 10px;font-size: 5px;padding:2px 4px">'+total+'</span>';
            }
            $span.html(title);
        }
    }
}
//获取选中菜单的子菜单
function getChildMenu(index,id){
	let p = $(".easyui-accordion").accordion('getPanel',index);
	$.ajax({
		url:"../nvaMenuAction/getMenuByPid",
		type:"post",
		data:{pid:id},
		dataType:"json",
		success:function(json){
			if(json.success){
				let html = "";
                let menus = json.data;
				for(let i=0;i<menus.length;i++){
                    let menu = menus[i];
                    let url = menu.url;
                    let text = menu.text;
					//var menuCaptionEn = menu.menucaptionen;
                    let menuImage = menu.menuimage;
					html += '<div class="childMenu" onclick="goPage(this);" id ="'+url+'" iconCls=\''+menuImage+'\'>'+
					 	   		'<span class="childMenuFont">'+text+'</span>'+
					 	    '</div>';
				}
				$(p).html(html);
                if(tag&&tag.data){
                    setTag()
                }
			}
		}
	});
}
/**********************WebScoket开始****************************/
var socket;
/*是否连接*/
var isconn;
$(function () {
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        console.log("您的浏览器支持WebSocket");
    }
})
socket = new WebSocket("ws://"+window.location.host+"/websocket");
/*打开事件  */
socket.onopen = function () {
    isconn = true;
};
/*获得消息事件  */
socket.onmessage = function (msg) {
    var data = msg.data;
    data = JSON.parse(msg.data)
    if(data.total>=0){
        //$('#mainTab').datagrid("loadData",data);
        tag.data = data;
        setTag()
    }/*else{
        $.messager.alert('系统提示',data.data,'info');
    }*/
};
/*关闭事件  */
socket.onclose = function () {
    /*console.log("Socket已关闭");  */
    isconn = false;
};
/*发生了错误事件  */
socket.onerror = function () {
    /* alert("Socket发生了错误"); */
    isconn = false;
}
$(window).on('beforeunload',function () {
    socket.close();
    isconn = false;
});
/***************************WebScoket完毕************************************/
/******************************************************/
//菜单跳转页面
function goPage(div){
	var menuName=$(div).children().html();
	var url=$(div).attr("id");
	var icon=$(div).attr("iconCls");
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
	if(currTab==null){
		return;
	}
	var url = $(currTab.panel('options').content).attr("src");
	if(url == undefined){
		url = "../loadMap";
	}
	$('#myEasyui-tabs').tabs('update', { 
		tab: currTab,
		options:{content:'<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:calc(100% - 6px);height:calc(100% - 6px);padding:3px;"></iframe>'}
	});

}

//首页
function goHome(){
	var menuName="实时监控";
	var url="../loadMap";
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
	if ($("#myEasyui-tabs" ).tabs('exists', title)) {  
        $('#myEasyui-tabs').tabs('select' , title);  
        console.log("ready to go review function.......")
        typeof refreshGridFunc === 'function' && refreshGridFunc.call();  
    }
}




//获取unitsession
function getUnitMessage(){
	var unitjson=$("#unit_json").val();
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


/**
 * 关闭修改信息模态框
 */
function closeModal(){
	$(".easyui-window").window("close");
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
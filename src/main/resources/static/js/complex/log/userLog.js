$(document).ready(function(){
	$("#mainTab").datagrid({
		onBeforeLoad : function(param){
			var page = param.page;
			var rows = param.rows;
			delete param.rows;
			delete param.page;
			param.pageIndex = page;
			param.pageSize = rows;
		},
		queryParams:{condition:condition},
		url:"../sysLogAction/list",
		autoRowHeight:false,
		remoteSort:true,
		pagination:true,
		pageList:[5,10,20,30,40],
		fitColumns:true,
		singleSelect:false,
		idField:"id",
		columns:[[
		          {field:'ch',width:5,checkbox:true},
		          {field:'id',width:50, title:'日志id',hidden:true,align:'center'},
		          {field:'userName',width:50,title:'操作用户',align:'center'},
		          {field:'clientip',width:50,title:'用户IP',align:'center'},
		          {field:'url',width:50,title:'操作模块',align:'center'},
		          {field:'urlfunction',width:50,title:'操作事件',align:'center'},
		          {field:'createTime',width:50,title:'访问时间',align:'center',sortable:true},
		          ]]
	})
})


//删除条件（1:id,2:date,3:field）
var condition = "";

/**
 * 导出所有数据
 */
function exportExcel(){
	condition = "id";
	$.messager.alert("提示","将要导出全部日志！","info",function(){
		window.location.href="../sysLogAction/export?condition="+condition;
	});
}




//删除
function remove(){
	var selectedIds= "";
	var checkedData = $("#mainTab").datagrid("getChecked");

	var selectedIds = "";
	for (var i in checkedData){
		if(selectedIds == ""){
			selectedIds = selectedIds + checkedData[i].id;
		}else{
			selectedIds = selectedIds + "," + checkedData[i].id;
		}
	}
	if(checkedData.length == 0 || typeof(checkedData) == "undefined"){
		$.messager.confirm("警告","将要清空日志，无法恢复，请谨慎操作！",function(result){
			if(result){
				deleteData(selectedIds)
			}
		});
	}else{

		$.messager.confirm("提示","删除无法恢复，请谨慎操作！",function(result){
			if(result){
				deleteData(selectedIds);
			}
		})
	}

}



function deleteData(ids){
	condition = "id";
	$.post("../sysLogAction/delete",{ids:ids,condition:condition},function(json){
		if(json.success){
			$.messager.alert("删除提示","删除成功","info",function(){
				refreshMyData();
			})
		}else{
			$.messager.alert("系统提示","删除失败","error");
		}
	},"json")
}

//页面刷新
function refreshMyData(){
	$("#mainTab").datagrid("reload");
}


/**
 * 控制伸缩面板
 */
function openPanel(){
	var status = $("#tabRowDetail").attr('collapsed');
	if(status == "true"){
		$(".easyui-layout").layout('expand','east');
		$("#tabRowDetail").attr('collapsed','false');
	}else{
		$(".easyui-layout").layout('collapse','east');
		$("#tabRowDetail").attr('collapsed','true');
	}
}





/**
 * 按日期删除
 */
function deleteByDate(){
	condition = "date";
	var start_time = $("#start_time").val();
	var end_time = $("#end_time").val();
	if(start_time != "" && end_time != ""){
		var js_start_time = new Date(start_time);
		var js_end_time = new Date(end_time);
		if(js_end_time < js_start_time){
			$.messager.alert("提示","起始日期不能超过结束日期","info");
			return;
		}
		$.post("../sysLogAction/delete",{startTime:start_time,endTime:end_time,condition:condition},function(json){
			if(json.success){
				$.messager.alert("删除提示","删除成功","info",function(){
					refreshMyData();
				})
			}else{
				$.messager.alert("系统提示","删除失败","error");
			}
		},"json")
	}else{
		$.messager.alert("提示","请选择日期！","info");
	}
}

/**
 * 按日期查询
 */
function queryByDate(){
	condition = "date";
	var start_time = $("#start_time").val();
	var end_time = $("#end_time").val();
	if(start_time != "" && end_time != ""){
		var js_start_time = new Date(start_time);
		var js_end_time = new Date(end_time);
		if(js_end_time < js_start_time){
			$.messager.alert("提示","起始日期不能超过结束日期","info");
			return;
		}
		$("#mainTab").datagrid("load",{startTime:start_time,endTime:end_time,condition:condition});
	}else{
		$.messager.alert("提示","请选择日期！","info");
	}
}


/**
 * 按日期导出
 */
function exportByDate(){
	condition = "date";
	var start_time = $("#start_time").val();
	var end_time = $("#end_time").val();
	if(start_time != "" && end_time != ""){
		var js_start_time = new Date(start_time);
		var js_end_time = new Date(end_time);
		if(js_end_time < js_start_time){
			$.messager.alert("提示","起始日期不能超过结束日期","info");
			return;
		}
		window.location.href="../sysLogAction/export?startTime="+start_time+"&endTime="+end_time+"&condition="+condition;
	}else{
		$.messager.alert("提示","请选择日期！","info");
	}
}

/**
 * 按照字段查询
 */
function queryByField(){

	condition = "field";
	var field = $("#field_select option:selected").val();
	var fieldValue = $("#field_input").val();
	if(fieldValue != ""){
		$("#mainTab").datagrid("load",{field:field,fieldValue:fieldValue,condition:condition});
	}
}

/**
 * 按字段删除
 */
function deleteByField(){
	condition = "field";
	var field = $("#field_select option:selected").val();
	var fieldValue = $("#field_input").val();
	if(fieldValue != ""){
		$.post("../sysLogAction/delete",{field:field,condition:condition,fieldValue:fieldValue},function(json){
			if(json.success){
				$.messager.alert("删除提示","删除成功","info",function(){
					refreshMyData();
				})
			}else{
				$.messager.alert("系统提示","删除失败","error");
			}
		},"json");
	}else{
		$.messager.alert("提示","请输入查询字段信息!","info");
	}
}

/**
 * 按字段导出
 */
function exportByField(){
	condition = "field";
	var field = $("#field_select option:selected").val();
	var fieldValue = $("#field_input").val();
	if(fieldValue != ""){
		window.location.href="../sysLogAction/export?field="+field+"&fieldValue="+fieldValue+"&condition="+condition;
	}else{
		$.messager.alert("提示","请输入字段信息！","info");
	}
}




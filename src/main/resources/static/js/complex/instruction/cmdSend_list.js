$(document).ready(function() {
	$('#mainTab').datagrid({
		onBeforeLoad : function(param) {
			var page = param.page; // 保存下值
			var rows = param.rows;
			delete param.rows; // 删掉
			delete param.page; // 删掉
			param.pageIndex = page; // 这里就是重新命名了
			param.pageSize = rows; // 这里就是重新命名了
		},
		url : '../cmdSendAction/list',
		autoRowHeight : false,
		nowrap : true,
		pagination : true,
		pageList : [ 10, 20, 30, 40, 100 ],
		fitColumns : false,
		checkOnSelect : true,
		singleSelect : false,
		// onSelect:function(rowIndex,rowData){//选择表格行触发
		// getDetail(rowIndex,rowData);
		// },
		idField : 'id',
		frozenColumns : [ [ {
			field : 'ck',
			width : 5,
			checkbox : true
		}, {
			title : 'id',
			field : 'id',
			width : 100,
			hidden : true
		}, {
			title : '指令类型',
			field : 'type',
			width : 150,
			align : 'center'
		}, {
			title : '指令tag',
			field : 'tag',
			width : 100,
			align : 'center'
		}]],
		columns : [ [ {
			title : '指令内容',
			field : 'cmdChar',
			align : 'left',
			//halign : 'center'
		}]]
	});
});

//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
      search:value
	});
}
//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}

//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}

//页面刷新(当前页)
function refreshPage(){
	 $('#mainTab').datagrid("reload");
}


//增加 （打开模态框）
function add(){
	$('#editIframe').attr('src','../cmdSendAction/toEdit');
	$('#editModal').window('setTitle','添加指令');
	$('#editModal').window('open');
}



//修改
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条指令！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$('#editIframe').attr('src','../cmdSendAction/toEdit?id='+v_id);
	$('#editModal').window('setTitle','修改指令');
	$('#editModal').window('open');
}

//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			deleteData(v_id);
		}
	});
}


//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length<1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			var arr = new Array(checkedData.length);
			for(i in checkedData){
				arr[i] = checkedData[i].id;
			}
			deleteData(arr);
		}
	});
}
function deleteData(ids){
	$.ajax({
		url: '../cmdSendAction/delete',
	    type: "post",
	    traditional:true,//用于传递数组
	    data: {ids:ids},
	    beforeSend: function () {
	    	 $.messager.progress({
	             title : '系统提示',
	             text : '请求中，请稍后。。。',
	             interval:300
	         });;	
	    },
	    success: function (data) {
			$.messager.progress('close');	
			if(data.success){
				$.messager.alert('删除提示','删除成功！','info',function(){refreshMyData();}); 
			}else {
				$.messager.alert('删除提示','删除失败！'+data,'error');
			}
	    },
	    error: function (data) {
	    	$.messager.alert('系统提示','异常：'+data,'error')
	    }
	});
}


function send(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length<1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请至少选择一条记录！','info');
		return;
	}
	var arr = new Array(checkedData.length);
	for(i in checkedData){
		arr[i] = checkedData[i].id;
	}
	sendCMD(arr)
}
function sendCMD(ids){
	$.ajax({
		type: "post",
	    data: {ids:ids},
	    url: "../cmdSendAction/send",
	    traditional:true,//用于传递数组
	    dataType:"json",
	    success: function (json) {
			if(json.success){
				$.messager.alert('发送提示','发送成功！','info',
					function(){
						refreshPage();
				});
			}
			else {$.messager.alert('发送提示','发送失败！','error');}
	    },
	    error: function (json) {
	    	$.messager.alert('系统提示','异常：'+json.data,'error')
	    }
	});
}
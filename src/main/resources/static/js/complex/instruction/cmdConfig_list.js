
$(document).ready(function() {
	$('#mainTab').datagrid({
		onBeforeLoad : function(param) {
			var page = param.page; // 保存下值
			var rows = param.rows;
			delete param.rows; // 删掉
			delete param.page; // 删掉
			param.pageIndex = page; // 这里就是重新命名了
			param.pageSize = rows; // 这里就是重新命名了
			if (param.sort == "devModel") {
				param.sort = "deviceModel.id";
			}
		},
		url : '../cmdConfigAction/list',
		autoRowHeight : false,
		nowrap : true,
		pagination : true,
		pageList : [ 10, 20, 30, 40, 100 ],
		fitColumns : false,
		checkOnSelect : true,
		singleSelect : true,
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
			width : 200,
			hidden : true
		}, {
			title : '指令中文名称',
			field : 'nameCh',
			width : 200,
			align : 'left',
			halign : 'center'
		},{
            title : '指令英文名称',
            field : 'nameEn',
            width : 150,
            align : 'left',
            halign : 'center'
        }, ] ],
		columns : [ [ {
			title : '指令标识符',
			field : 'tag',
			width : 100,
			align : 'center'
		}, {
			title : '指令长度',
			field : 'length',
			width : 70,
			align : 'center'
		}, {
			title : '内容配置',
			field : 'cmd',
			align : 'left'
		}, ] ]
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
	$('#editIframe').attr('src','../cmdConfigAction/toEdit');
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
	$('#editIframe').attr('src','../cmdConfigAction/toEdit?id='+v_id);
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
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			deleteData(checkedData[0].id);
		}
	});
}



function deleteData(id){
	$.ajax({
		url: '../cmdConfigAction/delete',
	    type: "post",
	    traditional:true,//用于传递数组
	    data: {id:id},
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
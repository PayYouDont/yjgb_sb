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
		url : '../cmdTypeAction/list',
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
			width : 100,
			hidden : true
		}, {
			title : '中文名称',
			field : 'nameCh',
			width : 150,
			align : 'left',
			halign : 'center'
		}, ] ],
		columns : [ [ {
			title : '英文名称',
			field : 'nameEn',
			width : 150,
			align : 'center'
		}, {
			title : '输入框类型',
			field : 'boxType',
			width : 100,
			align : 'center',
			formatter:function(val){
				var text = '';
				if(val==0){
					text = '文本框';
				}else if(val==1){
					text = '数组框';
				}else if(val==2){
					text = '日期框';
				}else if(val==3){
					text = '资源框';
				}
				return text;
			}
		}, {
			title : '资源名称',
			field : 'sourceUrl',
			width : 200,
			align : 'center',
			formatter:formatSourceUrl
		}, {
			title : '资源字段名',
			field : 'sourceFields',
			width : 200,
			align : 'center'
		}, ] ]
	});
});

function formatSourceUrl(value){
	if(value!=''){
		var url = '';
		if(value){
			$.ajax({
				url:'../menuAction/get',
				type:'post',
				dataType:'json',
				data:{id:value},
				async:false,
				success:function(json){
					if(json.success){
						url = json.data.text;
					}else{
						url = json.data
					}
				}
			});
		}
		return url;
	}
}

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
	$('#editIframe').attr('src','../cmdTypeAction/toEdit');
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
	$('#editIframe').attr('src','../cmdTypeAction/toEdit?id='+v_id);
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



function deleteData(id){
	$.ajax({
	    type: "post",
	    data: {id:id},
	    //contentType: "application/json",
	    url: '../cmdTypeAction/delete',
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
//	    complete: function () {//完成响应
//	        $("#submit").removeAttr("disabled");
//	    },
	    error: function (data) {
	    	$.messager.alert('系统提示','异常：'+data,'error')
	    }
	});
}

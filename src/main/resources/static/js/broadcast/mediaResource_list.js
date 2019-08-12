$(document).ready(function(){
		$('#mainTab').datagrid({   
			onBeforeLoad : function(param){  
				var page = param.page; //保存下值
				var rows = param.rows;
				delete param.rows; //删掉
				delete param.page; //删掉
				param.pageIndex = page; //这里就是重新命名了
				param.pageSize = rows; //这里就是重新命名了
			},
			queryParams: {
				listType:listType,
			},
			url:'../mediaResouceAction/list',
		    autoRowHeight:false,
		    nowrap:true,
			pagination:true,
			pageList:[10,20,30,40,100],
			fitColumns:false,
			checkOnSelect:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[
                {field:'id',title:'id',width:150,checkbox:true},
		        {field:'fileName',title:'资源名称',width:150,align:'center'}, 
		        {field:'status',title:'状态',width:150,align:'center',formatter:statusFormatter}, 
			]],
		    columns:[[
				{field:'playPath',title:'播放路径',width:300,align:'center',formatter:function (value) {
						return '<a href="'+value+'" target="view_window">'+value+'</a>';
				}},
		        {field:'filePath',title:'资源来源路径',width:150,align:'center'},
		        {field:'fileType',title:'资源类型',width:150,align:'center'},
				{field:'source',title:'来源',width:150,align:'center'},     
				{field:'createTime',title:'创建时间',width:150,align:'left',halign:'center'},
		        {field:'createBy',title:'创建人',width:150,align:'center'},
		        {field:'updateTime',title:'修改时间',width:150,align:'center'},
		        {field:'updateBy',title:'修改人',width:150,align:'center'},
		        {field:'introduction',title:'资源简介',width:150,align:'center',sortable:"true"},
		    ]]    
		});
});

function statusFormatter(value,rowData,rowIndex){
	if(value==0){
		return '<font style="color: #FF9800;">待审核</font>';
	}else if(value == 1){
		return '<font style="color:green;">审核通过</font>';
	}else if(value == 2){
		return '<font style="color:red;">审核未通过</font>';
	}
}


//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		search:value,
		listType:listType
	});
}


//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}


//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}


//页面刷新(当前页)
function refreshPage(){
	 $('#mainTab').datagrid("reload");
}

//增加
function add(){
	$('#editIframe').attr('src','../mediaResouceAction/toEdit?type='+listType);
	$('#editModal').window('setTitle','上传资源');
	$('#editModal').window('open');
}


//修改
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择媒体资源！','info');
		return;
	}
	var status = checkedData[0].status;
	if(status==1){
		$.messager.alert('选择提示','该资源已经通过审核，无法修改','info');
		return;
	}
	var v_id=checkedData[0].id;
	$('#editIframe').attr('src','../mediaResouceAction/toEdit?id='+v_id+"&type="+listType);
	$('#editModal').window('setTitle','修改资源');
	$('#editModal').window('open');
}

/**
 * 删除应急信息
 */
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	$.messager.confirm("删除提示","删除无法恢复，请谨慎操作！",function(result){
		if(result){
			deleteData(checkedData[0].id);
		}
	})
}

function deleteData(id){
	$.ajax({
	    type: "post",
	    data: {id:id},
	    url: "../mediaResouceAction/delete",
	    traditional:true,
	    dataType:"json",
	    success: function (json) {
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',
					function(){
						refreshPage();
				});
			}
			else {$.messager.alert('删除提示','删除失败！','error');}
	    },
	    error: function (json) {
	    	$.messager.alert('系统提示','异常：'+json.data,'error')
	    }
	});
}
//审核
function review(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$('#editIframe').attr('src','../mediaResouceAction/toEdit?id='+v_id+"&type="+listType);
	$('#editModal').window({
		onClose:function(){
			document.getElementById("editIframe").contentWindow.destroy();
		}
	});
	$('#editModal').window('setTitle','修改资源');
	$('#editModal').window('open');
}
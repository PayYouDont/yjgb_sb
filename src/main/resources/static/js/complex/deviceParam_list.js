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
			url:'../deviceModelParamAction/queryList',
			autoRowHeight:false,
		    nowrap:true,
			pagination:true,
			pageList:[10,20,30,40,100],
			fitColumns:false,
			checkOnSelect:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[
			                {field:'ck',width:5,checkbox:true},
					        {field:'id',title:'参数id',width:150,hidden:true},
					        {field:'paramName',title:'参数名称',width:250,align:'center'}, 
					        {field:'paramVariable',title:'参数变量',width:250,align:'center'}, 
						]],
			columns:[[
						{field:'createBy',title:'创建用户',width:150,align:'center',sortable:"true"}, 
				        {field:'createTime',title:'创建时间',width:150,align:'center',sortable:"true"}, 
				        {field:'updateBy',title:'修改用户',width:150,align:'center',sortable:"true"}, 
				        {field:'updateTime',title:'修改时间',width:150,align:'center',sortable:"true"}, 
				    ]]    
		});
});




//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		paramName:value
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


//增加 
function add(){
	$('#editIframe').attr('src','../deviceModelParamAction/toAdd');
	$('#editModal').window('setTitle','设备参数添加');
	$('#editModal').window('open');
}


//（打开模态框）
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var id=checkedData[0].id;
	$('#editIframe').attr('src','../deviceModelParamAction/updateUI?id='+id);
	$('#editModal').window('setTitle','设备参数修改');
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
	    type: "post",
	    data: {id:id},
	    dataType:"json",
	    url: "../deviceModelParamAction/delete",
	    success: function (json) {
			if(json.success){
				   $.messager.alert('删除提示','删除成功！','info',function(){
					   refreshPage();
				   }); 
			}else if(data=="error"){
				$.messager.alert('删除提示','删除失败！','error');
			}else if(data=="used"){
				$.messager.alert('删除提示','该参数正在被某型号使用','warning')
			};
	    },
//	    complete: function () {//完成响应
//	        $("#submit").removeAttr("disabled");
//	    },
	    error: function (data) {
	    	$.messager.alert('系统提示','异常：'+data,'error')
	    }
	});
}







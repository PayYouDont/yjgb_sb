$(document).ready(function(){
		$('#mainTab').datagrid({   
			onBeforeLoad : function(param){  
				var page = param.page; //保存下值
				var rows = param.rows;
				delete param.rows; //删掉
				delete param.page; //删掉
				param.pageIndex = page; //这里就是重新命名了
				param.pageSize = rows; //这里就是重新命名了
				if(param.sort=="devType"){
					param.sort="deviceType.id";
				}
			},
			url:'../deviceModelAction/queryList',
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
					        {field:'id',title:'设备型号id',width:150,hidden:true},
					        {field:'devmodel',title:'设备型号',width:150,align:'center'},
					        {field:'devType',title:'设备类型',width:150,align:'center',sortable:"true",
					        	formatter:function(value,row,index){
					        		return row.deviceType.devtype;
					        	}
					        }, 
					        {field:'devmodeldescription',title:'设备型号描述',width:300,align:'center'},
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
		devModel:value
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
	$('#editIframe').attr('src','../deviceModelAction/toEdit');
	$('#editModal').window('setTitle','设备型号添加');
	$('#editModal').window('open');
}




//（打开模态框）
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条信息！','info');
		return;
	}
	var id=checkedData[0].id;
	$('#editIframe').attr('src','../deviceModelAction/toEdit?id='+id);
	$('#editModal').window('setTitle','设备型号修改');
	$('#editModal').window('open');
}





//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条信息！','info');
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
	    //contentType: "application/json",
	    dataType:"json",
	    url: "../deviceModelAction/delete",
	    success: function (json) {
	    	var data = json.data;
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',function(){refreshPage();}); 
			}else{
				$.messager.alert('删除提示',data,'error');
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

















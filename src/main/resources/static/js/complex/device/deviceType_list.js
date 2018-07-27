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
			url:'../deviceTypeAction/list',
		    autoRowHeight:false,
		    fit:true,
			nowrap:true,
			pagination:true,
			pageList:[5,10,20,30,40],
			fitColumns:true,
			checkOnSelect:true,
			singleSelect:true,
			idField:'id',
		    columns:[[
		        {field:'ck',width:5,checkbox:true},
		        {field:'id',title:'设备类型id',width:50,hidden:true},
		        {field:'devtype',title:'设备类型',width:80,align:'center'}, 
		        {field:'devtypedescription',title:'设备类型描述',width:150,align:'center'}, 
		        {field:'createTime',title:'创建时间',width:50,align:'center',sortable:"true"}, 
		        {field:'createBy',title:'创建用户',width:50,align:'center',sortable:"true"}, 
		        {field:'updateTime',title:'修改时间',width:50,align:'center',sortable:"true"}, 
		        {field:'updateBy',title:'修改用户',width:50,align:'center',sortable:"true"}, 

		    ]]    
		});
});



//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		devType:value
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
	$('#editIframe').attr('src','../deviceTypeAction/toEdit');
	$('#editModal').window('setTitle','设备类型添加');
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
	$('#editIframe').attr('src','../deviceTypeAction/toEdit?id='+id);
	$('#editModal').window('setTitle','设备类型修改');
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
	    dataType:"json",
	    url: "../deviceTypeAction/delete",
	    success: function (json) {
	    	var data = json.data;
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',function(){
					refreshPage();
				});
			}else{
				$.messager.alert('删除提示',data,'warning')
			};
	    },
	    error: function (json) {
	    	$.messager.alert('系统提示','异常：'+json.data,'error')
	    }
	});
}



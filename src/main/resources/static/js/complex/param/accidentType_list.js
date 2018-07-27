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
		url:"../accidentTypeAction/list",
		fit:true,
		fitColumns:true,
		nowrap:true,
	    autoRowHeight:false,
		pagination:true,
		pageList:[10,20,30,40],
		checkOnSelect:true,
		singleSelect:true,
		idField:"id",
		columns:[[
		         {field:'ck',width:5,checkbox:true},
		         {field:'id',width:50,title:'事件类型id',hidden:true,align:'center'},
		         {field:'number',title:'类型序号',width:30,align:"center",sortable:true},
		         {field:'name',width:150,title:'类型名称',align:'center'},
		         {field:'code',width:150,title:'类型编码',align:'center',sortable:true},
		         {field:'createTime',title:'创建时间',width:100,align:'center',sortable:true}, 
			     {field:'createBy',title:'创建用户',width:100,align:'center'}, 
			     {field:'updateTime',title:'修改时间',width:100,align:'center',sortable:true}, 
			     {field:'updataBy',title:'修改用户',width:100,align:'center'}, 
		        ]]
	})
	
	
})

//关闭模态框
function closeMyModal(){
	$("#editModal").window('close');
}

//页面刷新
function refreshMyData(){
	$("#mainTab").datagrid("load");
}

//刷新（保留在当前页）
function refreshMyPage(){
	$("#mainTab").datagrid("reload");
}

//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		search:value
	});
}




function add(){
	$("#editIframe").attr("src","../accidentTypeAction/toEdit");
	$("#editModal").window("setTitle","事件类型添加");
	$("#editModal").window("open");
}



function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var v_accidentTypeId = checkedData[0].id;
	$("#editIframe").attr("src","../accidentTypeAction/toEdit?id="+v_accidentTypeId);
	$("#editModal").window("setTitle","事件类型修改");
	$("#editModal").window("open");
}





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
	$.post("../accidentTypeAction/delete",{id:id},function(data){
		if(data.success){
			$.messager.alert("删除提示","删除成功","info",function(){
				refreshMyPage();
			});
		}else{
			$.messager.alert("系统提示",data.data,"error");
		}
	},"json")
}



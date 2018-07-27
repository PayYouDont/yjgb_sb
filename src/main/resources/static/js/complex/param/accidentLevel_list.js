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
		url:"../accidentLevelAction/list",
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
		         {field:'id',width:50,title:'事件等级id',hidden:true,align:'center'},
		         {field:'number',width:30,title:'等级序号',align:'center',sortable:true},
		         {field:'level',width:150,title:'等级名称',align:'center'},
		         {field:'levelcode',width:150,title:'等级编码',align:'center',sortable:true},
		         {field:'createTime',title:'创建时间',width:100,align:'center',sortable:true}, 
		         {field:'createBy',title:'创建用户',width:100,align:'center'}, 
		         {field:'updateTime',title:'修改时间',width:100,align:'center',sortable:true}, 
		         {field:'updateBy',title:'修改用户',width:100,align:'center'}, 
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
	$("#editIframe").attr("src","../accidentLevelAction/toEdit");
	$("#editModal").window("setTitle","事件等级添加");
	$("#editModal").window("open");
}







function edit(){
	var checkedData = $("#mainTab").datagrid("getChecked");
	if(checkedData.length != 1 || typeof(checkedData) == "undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var v_accidentLevelId = checkedData[0].id;
	$("#editIframe").attr("src","../accidentLevelAction/toEdit?id="+v_accidentLevelId);
	$("#editModal").window("setTitle","事件类型修改");
	$("#editModal").window("open");
}


function remove(){
	var checkedData = $("#mainTab").datagrid("getChecked");
	if(checkedData.length != 1 || typeof(checkedData) == "undefined"){
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
	$.post("../accidentLevelAction/delete",{id:id},function(json){
		if(json.success){
			$.messager.alert("删除提示","删除成功","info",function(){
				refreshMyData();
			});
		}else{
			$.messager.alert("系统提示",json.data,"error");
		}
	},"json")
}


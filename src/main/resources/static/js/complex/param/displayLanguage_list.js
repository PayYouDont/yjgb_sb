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
		url:"../displayLanguageAction/list",
		autoRowHeight:false,
		pagination:true,
		pageList:[1,5,10,20,30,40],
		fitColumns:true,
		checkOnSelect:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){//选择表格行触发
			//getDetail(rowIndex,rowData);
		},
		idField:"id",
		columns:[[
		         {field:'ck',width:5,checkbox:true},
		         {field:'id',width:10,title:'播发语言id',hidden:true,align:'center'},
		         {field:'number',width:15,title:'序号',align:'center'},
		         {field:'language',width:50,title:'播发语言名称',align:'center'},
		         {field:'shortname',width:50,title:'播发语言简写',align:'center'},
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
	$("#mainTab").datagrid("reload");
}


function add(){
	$("#editIframe").attr("src","../displayLanguageAction/toEdit");
	$("#editModal").window("setTitle","添加播发语言");
	$("#editModal").window("open");
}



function edit(){
	var checkedData = $("#mainTab").datagrid("getChecked");
	if(checkedData.length != 1 || typeof(checkedData) == "undefined"){
		$.messager.alert('选择提示','请选择一条记录！','info');
		return;
	}
	var v_displayLanguageId = checkedData[0].id;
	$("#editIframe").attr("src","../displayLanguageAction/toEdit?id="+v_displayLanguageId);
	$("#editModal").window("setTitle","播发方式修改");
	$("#editModal").window("open");
}


function remove(){
	var checkedData = $('#mainTab').datagrid("getChecked");
	if(checkedData.length !=1 || typeof(checkedData) == "undefined"){
		$.messager.alert("选择提示","请选择一条记录！","info");
	}
	
	$.messager.confirm("删除提示","删除无法恢复，请谨慎操作！",function(result){
		if(result){
			deleteData(checkedData[0].id);
		}
	})
	
}


function deleteData(id){
	$.post("../displayLanguageAction/delete",{id:id},function(json){
		if(json.success){
			$.messager.alert("删除提示","删除成功","info",function(){
				refreshMyData();
			});
		}else{
			$.messager.alert("系统提示",jsin.data,"error");
		}
	},"json")
}

function refreshMyData(){
	$("#mainTab").datagrid("reload");
}

function doSearch(value){
	$("#mainTab").datagrid("load",{search:value});
}



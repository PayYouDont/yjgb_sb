$(document).ready(function() {
	var id="";
	$("#mainTab").treegrid({
		onBeforeLoad : function(){
			//alert(111)
		},
		url:"../administrativeAction/list",
		autoRowHeight:false,
		fitColumns:true,
		checkOnSelect:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){//选择表格行触发
			//getDetail(rowIndex,rowData);
		},
		idField:"id",
		treeField:"name",
		columns:[[
		         {field:'name',width:420,title:"区域名称"},
		         {field:'code',width:250,title:'编码',align:'center'},
		         {field:'longitude',width:120,title:'经度',align:'center'},
		         {field:'latitude',width:120,title:'纬度',align:'center'},
		         {field:'codeLevel',width:120,title:'区域级别',align:'center'},
		         ]]
	})
});


function loadTree(){
	isAll = false;
	
}

function doSearch(searchCondition){
	if(searchCondition == "" || searchCondition == null){
		return;
	}
	$("#mainTab").treegrid("load",{searchCondition:searchCondition});
}



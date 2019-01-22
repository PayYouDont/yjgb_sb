$(document).ready(function(){
	$('#mainTab').treegrid({    
	    url:'../cmdConfigAction/list', 
	    autoRowHeight:false,
	    fit:true,
		fitColumns:false,
		checkOnSelect:true,
		singleSelect:true,
	    idField:'id',    
	    treeField:'text',
	    nowrap:true,
        striped:true,
		frozenColumns:[[
			 			{field:'ck',width:5,checkbox:true},
		    	        {title:'id',field:'id',width:200,hidden:true},
		    	        {title:'指令名称',field:'text',width:200,align:'left',halign:'center'},
					]],
	    columns:[[
	    	{title:'指令标识符',field:'tag',width:100,align:'center'},
	    	{title:'指令长度',field:'length',width:200,align:'center'},
	    	{title:'内容配置',field:'cmd',width:300,align:'left',halign:'center'},
	    ]]    
	});  
});



//系统归属格式
function  systemFormatter(value,row,index){
	if(value == 0){
		return "综合网络管理系统"
	}else{
		return "应急播发管理系统"
	}
}



//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}

//页面刷新
function refreshMyData(){
	 $('#mainTab').treegrid("reload");
}


//增加 （打开模态框）
function add(){

	$('#editIframe').attr('src','../cmdConfigAction/toEdit');
	$('#editModal').window('setTitle','添加指令');
	$('#editModal').window('open');
}





//（打开模态框）
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择指令！','info');
		return;
	}
	var v_id=checkedData[0].id;
	var v_level=$('#mainTab').treegrid('getLevel',v_id);
	if(v_level=="1"){
		$.messager.alert('选择提示','该菜单不能操作！','info');
		return;
	}
	$('#editIframe').attr('src','../cmdConfigAction/toEdit?id='+v_id);
	$('#editModal').window('setTitle','指令修改');
	$('#editModal').window('open');
}




//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
        search:value
	});
}


//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择指令！','info');
		return;
	}
	var v_id=checkedData[0].id;
	var v_menuSystem=checkedData[0].menuSystem;
	var v_level=$('#mainTab').treegrid('getLevel',v_id);
	if(v_level=="1"){
		$.messager.alert('选择提示','该指令不能操作！','info');
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
	    url: "../cmdConfigAction/delete",
	    dataType:"json",
	    beforeSend: function () {
	    	 $.messager.progress({
	             title : '系统提示',
	             text : '请求中，请稍后。。。',
	             interval:300
	         });;	
	    },
	    success: function (json) {
			$.messager.progress('close');	
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',function(){refreshMyData();}); 
			}else {
				$.messager.alert('删除提示','删除失败！','error');
			}
	    },
	    error: function (data) {
			$.messager.progress('close');	
	    	$.messager.alert('系统提示','异常：'+data,'error')
	    }
	});
}



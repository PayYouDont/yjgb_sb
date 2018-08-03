$(document).ready(function(){
	$('#mainTab').treegrid({    
	    url:'../menuAction/list', 
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
		    	        {title:'pid',field:'pid',width:200,align:'center',hidden:true}, 
		    	        {title:'菜单名称',field:'text',width:200,align:'left',halign:'center'},
					]],
	    columns:[[
			{title:'英文标题',field:'menucaptionen',width:200,align:'center'},
	        {title:'菜单图标',field:'menuimage',width:200,align:'center'},
	        {title:'连接地址',field:'url',width:300,align:'left',halign:'center'},
	        {title:'说明信息',field:'menucaptionhint',width:200,align:'left',halign:'center'},
	        {title:'系统归属',field:'menusystem',width:200,align:'center',halign:'center',formatter:systemFormatter},
	        {title:'顺序编号',field:'number',width:100,align:'center'},

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

	$('#editIframe').attr('src','../menuAction/toAdd');
	$('#editModal').window('setTitle','菜单添加');
	$('#editModal').window('open');
}





//（打开模态框）
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择菜单！','info');
		return;
	}
	var v_id=checkedData[0].id;
	var v_menuSystem=checkedData[0].menuSystem;
	var v_level=$('#mainTab').treegrid('getLevel',v_id);
	if(v_level=="1"){
		$.messager.alert('选择提示','该菜单不能操作！','info');
		return;
	}
	$('#editIframe').attr('src','../menuAction/toAdd?id='+v_id);
	$('#editModal').window('setTitle','菜单修改');
	$('#editModal').window('open');
}







//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择菜单！','info');
		return;
	}
	var v_id=checkedData[0].id;
	var v_menuSystem=checkedData[0].menuSystem;
	var v_level=$('#mainTab').treegrid('getLevel',v_id);
	if(v_level=="1"){
		$.messager.alert('选择提示','该菜单不能操作！','info');
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
	    url: "../menuAction/delete",
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



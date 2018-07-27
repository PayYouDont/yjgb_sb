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
			url:'../userAction/querryUser',
		    autoRowHeight:false,
		    fit:true,
		    nowrap:true,
			fitColumns:false,
			pagination:true,
			pageList:[10,20,30,40],
			checkOnSelect:true,
			singleSelect:true,
			onLoadSuccess:function(data){
			},
			idField:'id',
			frozenColumns:[[
			                {field:'id',title:'用户id',width:150,checkbox:true},
					        {field:'name',title:'用户名',width:150,align:'center'}, 
					        {field:'phone',title:'联系方式',width:150,align:'center'}, 
					        {field:'roleGroupInfoName',title:'角色',width:150,align:'center'}, 
					        {field:'areaCodeName',title:'管理区域',width:150,align:'center',sortable:true}
						]],
		    columns:[[
		        {field:'areaCode',title:'管理区域编码',width:150,align:'center',sortable:true}, 
		        {field:'sex',title:'性别',width:150,align:'center',formatter:sexFormatter}, 
		        {field:'createTime',title:'创建时间',width:150,align:'center',sortable:true}, 
		        {field:'createBy',title:'创建用户',width:150,align:'center'}, 
		        {field:'updateTime',title:'修改时间',width:150,align:'center',sortable:true}, 
		        {field:'updateBy',title:'修改用户',width:150,align:'center'}, 
		    ]]    
		});
		
});

function sexFormatter(value,row,index){
	if(value == 0){
		return "女"
	}else{
		return "男"
	}
}


//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		userName:value
	});
}




//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}

//页面表格数据全部重新加载
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}

//页面表格数据重新加载，但保持在当前页
function refreshPage(){
	 $('#mainTab').datagrid("reload");
}



//增加 
function add(){
	$('#editIframe').attr('src','../userAction/toEdit');
	$('#editModal').window('setTitle','用户添加');
	$('#editModal').window('open');
}


//修改
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择信息！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$('#editIframe').attr('src','../userAction/toEdit?id='+v_id);
	$('#editModal').window('setTitle','用户修改');
	$('#editModal').window('open');
}

$.extend($.fn.validatebox.defaults.rules, {    
    pass: {    
        validator: function(value,param){
        	var reg = /^[\w]{6,18}$/;  
        	return reg.test(value);
        },    
        message: '密码只能是6到18位的数字 ，字母和下划线的任意组合!'   
    }    
});


//修改密码
function editPass(){
	var reg = /^[\w]{6,18}$/;
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一行信息！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$.messager.prompt('用户密码修改', '请输入要修改的密码：', function(r){
		if (reg.test(r)){
			 $.post("../userAction/editPass",{id:v_id,userPassword:r},function(data){
				  if(data=="ok"){
					   $.messager.alert('系统提示','修改成功！','info',function(){
						   refreshPage();
					   }); 
				   }
				   else{$.messager.alert('系统提示','修改失败！','error');}
			   });
		}else{
			$.messager.alert('系统提示','请输入6到18位的数字 ，字母和下划线的任意组合!','warning');
		}
	});

	
}





//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择信息！','info');
		return;
	}
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			deleteData(checkedData[0].id);
		}
	});
}

function deleteData(id){
  $.post("../userAction/delete",{id:id},function(json){
	  if(json.success){
		   $.messager.alert('删除提示','删除成功！','info',function(){
			   refreshPage();
		   }); 
	   }
	   else{$.messager.alert('删除提示','删除失败！','error');}
   },"json");
}














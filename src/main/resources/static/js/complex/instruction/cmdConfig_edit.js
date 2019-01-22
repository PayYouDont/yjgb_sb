$(document).ready(function(){
	initTab();
});
function initTab() {
	$('#mainTab').datagrid({   
		onBeforeLoad : function(param){  
			var page = param.page; //保存下值
			var rows = param.rows;
			delete param.rows; //删掉
			delete param.page; //删掉
			param.pageIndex = page; //这里就是重新命名了
			param.pageSize = rows; //这里就是重新命名了
		},
		url:'../cmdConfigAction/list',
	    autoRowHeight:false,
	    fit:true,
	    nowrap:true,
		fitColumns:false,
		pagination:true,
		pageList:[10,20,30,40],
		checkOnSelect:false,
		singleSelect:false,
		toolbar: [{
			text: '添加指令内容', 
			iconCls: 'icon-add',
			handler: append
		},'-', {
            text: '删除指令', 
            iconCls: 'icon-remove', 
            handler: removeit
        },'-', {
            text: '保存', 
            iconCls: 'icon-save', 
            handler: accept
        }],
		idField:'id',
		frozenColumns:[[
            {title:"序号",field:'ck',width:5,checkbox:true},
            {title:'属性类型',field:'type',width:130,align:'center',formatter:function(value,row){
            	if(value){
                	return cmdType[row.type].text;
            	}
			},editor:{
            	type:'combobox',
            	options:{
            		data: cmdType, 
            		valueField: "value", 
            		textField: "text",
            		required:true
            	}
            }}
        ]],
	    columns:[[
	    	{title:'属性名',field:'attrName',width:150,align:'left',halign:'center',editor:{
            	type:'validatebox',
            	options:{
            		required:true
            	}
            }},
	    	//{title:'属性值',field:'value',width:150,align:'left',halign:'center'},
	    	{title:'长度(bit)',field:'length',width:60,align:'center',editor:{
	    		type:'validatebox',
            	options:{
            		required:true
            	}
	    	}},
	        {title:'说明',field:'explain',width:220,align:'left',halign:'center',editor:{type:'validatebox',
            	options:{
            		required:false
            	}
	    	}}
	    ]],
		onClickRow:onClickRow,
	});
}
var cmdType = [
	{ "value": "0", "text": "时钟校准" },
	{ "value": "1", "text": "资源编码设置" },
	{ "value": "2", "text": "锁定频率" },
	{ "value": "3", "text": "回传方式/地址" },
	{ "value": "4", "text": "回传周期" },
	{ "value": "5", "text": "默认音量" },
	{ "value": "6", "text": "状态/参数查询" },
];
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#mainTab').datagrid('validateRow', editIndex)){
		$('#mainTab').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#mainTab').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#mainTab').datagrid('selectRow', editIndex);
		}
	}
}
function append(){
	if (endEditing()){
		$('#mainTab').datagrid('appendRow',{});
		editIndex = $('#mainTab').datagrid('getRows').length-1;
		$('#mainTab').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
function removeit(){
	if (editIndex == undefined){return}
	$('#mainTab').datagrid('cancelEdit', editIndex)
			.datagrid('deleteRow', editIndex);
	editIndex = undefined;
}
function accept(){
	if (endEditing()){
		$('#mainTab').datagrid('acceptChanges');
	}
}
function reject(){
	$('#mainTab').datagrid('rejectChanges');
	editIndex = undefined;
}
function getChanges(){
	var rows = $('#mainTab').datagrid('getChanges');
	alert(rows.length+' rows are changed!');
}




//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}

//刷新父级页面数据
 function refreshParentData(){
		window.parent.refreshMyData();
 }


 
 
 
//保存
function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../cmdConfigAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(json){
			data = JSON.parse(json);
			$.messager.progress('close');	
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					closeParentModal();
					refreshParentData();
				});
			}else{
				$.messager.progress('close');	
				$.messager.alert('异常', '保存失败!','error');
			}
		}
	});
}

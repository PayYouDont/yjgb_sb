
$(document).ready(function(){
	initTab();
});
function initTab() {
	datagrid = $('#cmdTab').datagrid({   
	    autoRowHeight:false,
	    fit:true,
	    nowrap:true,
		fitColumns:false,
		pagination:true,
		pageList:[10,20,30,40],
		checkOnSelect:false,
		singleSelect:true,
		toolbar: [{
			text: '添加指令内容', 
			iconCls: 'icon-add',
			handler: append
		},'-', {
            text: '删除指令', 
            iconCls: 'icon-remove', 
            handler: remove
        },'-', {
            text: '保存', 
            iconCls: 'icon-save', 
            handler: accept
        }],
		idField:'id',
		frozenColumns:[[
            {title:"序号",field:'ck',width:5,checkbox:true}
        ]],
	    columns:[[
	    	{title:'属性类型',field:'attrType',width:150,align:'center',
	    		formatter:function(value,row,index){
	    			if(value&&value.trim()!=''){
	    				var type = getCmdType(value);
		    			return type.nameCh
	    			}
	    	}},
	    	{title:'属性名',field:'attrName',width:150,align:'center',editor:{
            	type:'validatebox',
            	options:{
            		required:true
            	}
            }},
	    	//{title:'属性值',field:'attrValue',width:150,align:'center'},
	        {title:'说明',field:'explain',width:250,align:'center',editor:{
            	type:'textbox'
            }}
	    ]],
	    onClickCell:onClickCell,
	    onDblClickCell:onDblClickCell
	});
	var id = $("#id").val();
	if(id){
		$('#cmdTab').datagrid('loadData',cmd);
	}
}

function append(){
	var value = $("#cmdTypeId").combobox("getValue");
	if(value.trim()==''){
		$.messager.alert('警告', '请选择属性类!','warning');
		return;
	}
	if (endEditing()){
		$('#cmdTab').datagrid('appendRow',{
			attrType:value
		});
		editIndex = $('#cmdTab').datagrid('getRows').length-1;
		$('#cmdTab').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
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
	var tabData = $('#cmdTab').datagrid("getData");
	if (tabData.rows.length == 0) {
		$.messager.alert('警告', '请添加指令内容!', 'warning');
		return;
	}
	if (!endEditing()) {
		$.messager.alert('警告', '请先保存指令内容!', 'warning');
		return;
	}
	$.messager.progress({
		title : '系统提示',
		text : '信息提交，请稍后。。。',
		interval : 300
	});
	$('#dataForm').form('submit',{
		url:'../cmdConfigAction/save',
		onSubmit:function(param){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			param.cmd = JSON.stringify(tabData);
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
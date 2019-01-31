$.extend($.fn.datagrid.methods, {
    addEditor : function(jq, param) {
        return jq.each(function(){
            if (param instanceof Array) {
                $.each(param, function(index, item) {
                    var e = $(jq).datagrid('getColumnOption', item.field);
                    e.editor = item.editor;
                });
            } else {
                var e = $(jq).datagrid('getColumnOption', param.field);
                e.editor = param.editor;
            }
        });
    },
    removeEditor : function(jq, param) {
        return jq.each(function(){
            if (param instanceof Array) {
                $.each(param, function(index, item) {
                    var e = $(jq).datagrid('getColumnOption', item);
                    e.editor = {};
                });
            } else {
                var e = $(jq).datagrid('getColumnOption', param);
                e.editor = {};
            }
        });
    }
});
$(document).ready(function(){
	initTab();
});
function initTab() {
	var datagrid = $('#cmdTab').datagrid({   
		onBeforeLoad : function(param){  
			var page = param.page; //保存下值
			var rows = param.rows;
			delete param.rows; //删掉
			delete param.page; //删掉
			param.pageIndex = page; //这里就是重新命名了
			param.pageSize = rows; //这里就是重新命名了
		},
		//url:'../cmdConfigAction/list',
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
	    	{title:'属性类型',field:'attrType',width:150,align:'center'},
	    	{title:'属性名',field:'attrName',width:150,align:'center',editor:{
            	type:'validatebox',
            	options:{
            		required:true
            	}
            }},
	    	{title:'属性值',field:'attrValue',width:150,align:'center',editor:{
	    		type:'textbox',
            	options:{
            		required:true
            	}
	    	}},
	        {title:'说明',field:'explain',width:250,align:'center',editor:{type:'validatebox',
            	options:{
            		required:false
            	}
	    	}}
	    ]],
	    onClickCell:onClickCell,
	    onDblClickCell:onDblClickCell,
	    onBeforeEdit:onBeforeEdit
	});
	var id = $("#id").val();
	if(id){
		$('#cmdTab').datagrid('loadData',cmd);
	}
}
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){
		return true
	}
	if ($('#cmdTab').datagrid('validateRow', editIndex)){
		$('#cmdTab').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	}
	return false;
}
function onClickCell(rowIndex, field, value){
	if (editIndex != rowIndex){
		if (endEditing()){
			$('#cmdTab').datagrid('endEdit', editIndex);
			editIndex = undefined;
		} 
		$('#cmdTab').datagrid('selectRow', rowIndex).datagrid('checkRow', rowIndex);
	}
}
function onDblClickCell(index){
	if (editIndex != index){
		if (endEditing()){
			$('#cmdTab').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#cmdTab').datagrid('selectRow', editIndex);
		}
	}
}
function append(){
	var text = $("#type").combobox("getText");
	if(text.trim()==''){
		$.messager.alert('警告', '请选择属性类!','warning');
		return;
	}
	if (endEditing()){
		$('#cmdTab').datagrid('appendRow',{
			attrType:text
		});
		editIndex = $('#cmdTab').datagrid('getRows').length-1;
		$('#cmdTab').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
function remove(){
	if (editIndex == undefined){
		var selected = $('#cmdTab').datagrid('getSelected');
		if(selected){
			var index = $('#cmdTab').datagrid('getRowIndex',selected);
			$('#cmdTab').datagrid('deleteRow',index);
		}
	}else{
		$('#cmdTab').datagrid('cancelEdit', editIndex)
		.datagrid('deleteRow', editIndex);
		editIndex = undefined;	
	}
}
function accept(){
	if (endEditing()){
		$('#cmdTab').datagrid('acceptChanges');
	}
}
function reject(){
	$('#cmdTab').datagrid('rejectChanges');
	editIndex = undefined;
}
function getChanges(){
	var rows = $('#cmdTab').datagrid('getChanges');
	alert(rows.length+' rows are changed!');
}

function onBeforeEdit(rowIndex, rowData){
	var type = $("#type").combobox("getValue");
	$("#cmdTab").datagrid('removeEditor','attrValue');//这里的cardNo是需要移除editor的列的field值
	if(type==0){//文本
		$("#cmdTab").datagrid('addEditor',[ //添加cardNo列editor
            {field:'attrValue',editor:{
                type:'textbox',
                options:{
                    required:true,
                }
            }
        }]);
	}else if(type==1){//数字
		$("#cmdTab").datagrid('addEditor',[ //添加cardNo列editor
            {field:'attrValue',editor:{
                type:'numberbox',
                options:{
                    required:true,
                }
            }
        }]);
	}else if(type==2){//日期
		$("#cmdTab").datagrid('addEditor',[ //添加cardNo列editor
            {field:'attrValue',editor:{
                type:'datetimebox',
                options:{
                    required:true,
                }
            }
        }]);
	}else if(type==3){//资源
		$("#cmdTab").datagrid('addEditor',[ //添加cardNo列editor
            {field:'attrValue',editor:{
                type:'combobox',
                options:{
                    required:true,
                }
            }
        }]);
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
	 if(tabData.rows.length==0){
		 $.messager.alert('警告', '请添加指令内容!','warning');
		 return;
	 }
	 if(!endEditing()){
		 $.messager.alert('警告', '请先保存指令内容!','warning');
		 return;
	 }
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../cmdConfigAction/save',
		onSubmit:function(param){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			var data = $('#cmdTab').datagrid("getData");
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
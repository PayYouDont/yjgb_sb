//关闭父级页面模态框
function closeParentModal() {
	window.parent.closeMyModal();
}

// 刷新父级页面数据
function refreshParentData() {
	window.parent.refreshMyData();
}
$(document).ready(function() {
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
            text: '保存', 
            iconCls: 'icon-save', 
            handler: accept
        }],
		idField:'id',
	    columns:[[
	    	{title:'属性类型',field:'attrType',width:150,hidden : true},
	    	{title:'属性名',field:'attrName',width:150,align:'center'},
	    	{title:'属性值',field:'attrValue',width:150,align:'center'},
	        {title:'取值说明',field:'explain',width:250,align:'center'}
	    ]],
	    onDblClickCell:onDblClickCell,
	    onBeforeEdit:onBeforeEdit
	});
	$('#cmdConfigId').combobox({
		onLoadSuccess : function() {
			var id = $('#cmdConfigId').combobox('getValue');
			getTag(id);
		},
		onChange : function(id) {
			getTag(id);
		}
	})
});
var resourceData = {};
function onBeforeEdit(rowIndex, rowData) {
	var id = rowData.attrType;
	var cmdType = getCmdType(id);
	var type = cmdType.nameCh;
	if (type == '文本') {// 文本
		datagrid.datagrid('addEditor', [ {
			field : 'attrValue',
			editor : {
				type : 'textbox',
				options : {
					required : true,
				}
			}
		} ]);
	} else if (type == '数字') {// 数字
		datagrid.datagrid('addEditor', [ { // 添加cardNo列editor
			field : 'attrValue',
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					type : 'number'
				}
			}
		} ]);
	} else if (type == '日期') {// 日期
		datagrid.datagrid('addEditor', [ { // 添加cardNo列editor
			field : 'attrValue',
			editor : {
				type : 'datetimebox',
				options : {
					required : true,
				}
			}
		} ]);
	} else {// 资源
		setResourceData(cmdType)
		datagrid.datagrid('addEditor', [ {// 添加cardNo列editor
			field : 'attrValue',
			editor : {
				type : 'combobox',
				options : {
					required : true,
					data : resourceData.data,
					valueField : resourceData.field,
					textField : resourceData.field,
				}
			}
		} ]);
	}
}
function setResourceData(cmdType) {
	var url = cmdType.sourceUrl;
	resourceData.field = cmdType.sourceFields;
	if (url&&url != '') {
		url = url.replace('toList','list')
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:{pageIndex:1,pageSize:100},
			async : false,
			success:function(json){
				resourceData.data = json.rows;
			}
		})
	}
}

function getTag(configId) {
	$.ajax({
		url : '../cmdConfigAction/get',
		type : 'post',
		dataType : 'json',
		data : {
			id : configId
		},
		success : function(json) {
			if (json.success) {
				var tag = json.data.tag;
				$('#tag').textbox('setValue', tag);
				var id = $("#id").val();
				if(id){
					$('#cmdTab').datagrid('loadData',cmdChar);
				}else{
					var cmd = json.data.cmd;
					cmd = JSON.parse(cmd);
					$('#cmdTab').datagrid('loadData',cmd);
				}
			}
		}
	});
}
// 保存
function mysave() {
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
	$.messager.progress({
		title : '系统提示',
		text : '信息提交，请稍后。。。',
		interval : 300
	});
	$('#dataForm').form('submit', {
		url : '../cmdSendAction/save',
		onSubmit : function(param) {
			var isValid = $(this).form('enableValidation').form('validate');
			if (!isValid) {
				$.messager.progress('close');
			}
			param.cmdChar = JSON.stringify(tabData);
			return isValid;
		},
		success : function(json) {
			data = JSON.parse(json);
			$.messager.progress('close');
			if (data.success) {
				$.messager.alert('系统提示', '保存成功!', 'info', function() {
					closeParentModal();
					refreshParentData();
				});
			} else {
				$.messager.progress('close');
				$.messager.alert('异常', '保存失败!', 'error');
			}
		}
	});
}

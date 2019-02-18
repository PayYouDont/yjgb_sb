var datagrid;
var editIndex = undefined;
$.extend($.fn.datagrid.methods, {
	addEditor : function(jq, param) {
		return jq.each(function() {
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
		return jq.each(function() {
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

function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if (datagrid.datagrid('validateRow', editIndex)) {
		datagrid.datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	}
	return false;
}
function onClickCell(rowIndex, field, value) {
	if (editIndex != rowIndex) {
		if (endEditing()) {
			datagrid.datagrid('endEdit', editIndex);
			editIndex = undefined;
		}
		datagrid.datagrid('selectRow', rowIndex).datagrid('checkRow', rowIndex);
	}
}
function onDblClickCell(index) {
	if (editIndex != index) {
		if (endEditing()) {
			datagrid.datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		} else {
			datagrid.datagrid('selectRow', editIndex);
		}
	}
}

function remove() {
	if (editIndex == undefined) {
		var selected = datagrid.datagrid('getSelected');
		if (selected) {
			var index = datagrid.datagrid('getRowIndex', selected);
			datagrid.datagrid('deleteRow', index);
		}
	} else {
		datagrid.datagrid('cancelEdit', editIndex).datagrid('deleteRow',
				editIndex);
		editIndex = undefined;
	}
}
function accept() {
	if (endEditing()) {
		datagrid.datagrid('acceptChanges');
	}
}
function reject() {
	datagrid.datagrid('rejectChanges');
	editIndex = undefined;
}
function getChanges() {
	var rows = datagrid.datagrid('getChanges');
	alert(rows.length + ' rows are changed!');
}

function getCmdType(id){
	var cmdType;
	$.ajax({
		url : '../cmdTypeAction/get',
		type : 'post',
		dataType : 'json',
		data : {
			id : id
		},
		async : false,
		success : function(json) {
			if (json.success) {
				cmdType = json.data;
			}
		}
	});
	return cmdType;
}
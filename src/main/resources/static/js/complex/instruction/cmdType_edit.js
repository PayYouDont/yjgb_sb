//关闭父级页面模态框
function closeParentModal() {
	window.parent.closeMyModal();
}

//刷新父级页面数据
function refreshParentData() {
	window.parent.refreshMyData();
}
$(function() {
	$("#boxType").combobox({
		onLoadSuccess : function() {
			var val = $("#boxType option:selected").val();
			if (val == 3) {
				$("#resource_tr").show();
				$('#sourceUrl').combotree({ required: true });
				$("#sourceFields").combobox({ required: true })
			} else {
				$("#resource_tr").hide();
				$('#sourceUrl').combotree({ required: false });
				$('#sourceUrl').combotree('clear')
				$("#sourceFields").combobox({ required: false })
				$('#sourceFields').combobox('clear')
			}
		},
		onChange : function() {
			var val = $("#boxType option:selected").val();
			if (val == 3) {
				$("#resource_tr").show();
				$('#sourceUrl').combotree({ required: true });
				$("#sourceFields").combobox({ required: true })
			} else {
				$("#resource_tr").hide();
				$('#sourceUrl').combotree({ required: false });
				$('#sourceUrl').combotree('clear')
				$("#sourceFields").combobox({ required: false })
				$('#sourceFields').combobox('clear')
			}
		}
	})
	$("#sourceUrl").combotree({
		onLoadSuccess : function() {
			//选中
			var val = $("#sourceUrl").attr("value");
			$("#sourceUrl").combotree("setValue",val);
		},
		onChange : function() {
			var t = $("#sourceUrl").combotree('tree');
			var menu = t.tree('getSelected');
			var url = menu.url;
			$("#resourceFrame").attr('src', url);
			var iframe = document.getElementById("resourceFrame");
			var datagrid;
			var content = iframe.contentWindow;
			if (iframe.attachEvent) {
				iframe.attachEvent("onload", function() {//ie  
					//iframe加载完成后你需要进行的操作  
					datagrid = content.$("#mainTab");
					loadFields(datagrid);
				});
			} else {
				iframe.onload = function() {
					//iframe加载完成后你需要进行的操作
					datagrid = content.$("#mainTab");
					loadFields(datagrid);
				};
			}
		}
	});
})
function loadFields(datagrid) {
	if (datagrid) {
		var columns = datagrid.datagrid("options").columns[0];
		var frozenColumns = datagrid.datagrid("options").frozenColumns[0];
		var data = addData(frozenColumns);
		data = data.concat(addData(columns));
		$("#sourceFields").combobox({
			valueField : 'field',
			textField : 'title',
			data : data,
			onLoadSuccess : function() {
				//选中
				var val = $("#sourceFields").attr("value");
				$("#sourceFields").combobox("setValue",val);
			},
		});
		$(".resource-field").show();
	} else {
		$.messager.alert('选择提示', '该资资源没有可用属性！', 'warning');
		$(".resource-field").hide();
		$("#sourceFields").combobox("clear")
	}
}
function addData(colArr) {
	var arr = new Array();
	for (var i = 0; i < colArr.length; i++) {
		var c = colArr[i];
		if (c.hidden) {
			continue;
		}
		arr.push({
			title : c.title,
			field : c.field
		});
	}
	return arr;
}
//保存
function mysave() {
	$.messager.progress({
		title : '系统提示',
		text : '信息提交，请稍后。。。',
		interval : 300
	});
	$('#dataForm').form('submit', {
		url : '../cmdTypeAction/save',
		onSubmit : function() {
			/*if($('#resource_tr').is(':hidden')){
				//$('#sourceUrl,#sourceFields').combo({ required: false });
				$('#sourceUrl').combotree({ required: false });
				$('#sourceFields').combotree({ required: false });
				
			}else{
				//$('#sourceUrl,#sourceFields').combo({ required: true });
				$('#sourceUrl').combotree({ required: true });
				$('#sourceFields').combotree({ required: false });
			}*/
			var isValid = $(this).form('enableValidation').form('validate');
			if (!isValid) {
				$.messager.progress('close');
			}
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
		},
		error : function(e) {
			$.messager.progress('close');
			$.messager.alert('异常', '保存失败!', 'error');
		}
	});
}

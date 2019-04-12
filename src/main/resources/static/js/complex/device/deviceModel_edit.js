$(document).ready(function(){
		$('#mainTab').datagrid({   
			url:'../deviceModelParamAction/list',
		    autoRowHeight:false,
			fitColumns:true,
			checkOnSelect:true,
//			singleSelect:true,
			onSelect:function(rowIndex,rowData){//选择表格行触发
				selectDevModelParms(rowIndex,rowData);
			},
			onUnselect:function(rowIndex,rowData){//选择表格行触发
				selectDevModelParms(rowIndex,rowData);
			},
			onSelectAll:function(rowIndex,rowData){//选择表格行触发
				selectDevModelParms(rowIndex,rowData);
			},
			onUnselectAll:function(rowIndex,rowData){//选择表格行触发
				selectDevModelParms(rowIndex,rowData);
			},
			onLoadSuccess:function(data){//数据加载成功触发
				selectRows();
			},
			idField:'id',
		    columns:[[
		        {field:'ck',width:5,checkbox:true},
		        {field:'id',title:'参数id',width:50,hidden:true},
		        {field:'paramName',title:'参数名称',width:50,align:'center'}, 
		        {field:'paramVariable',title:'参数变量',width:50,align:'center'}
		    ]]    
		});
	
})


//列表选择回显
function selectRows(){
	//列表选择回显
	var v_devModelParamIds=$('#devModelParamIds').val();
	var ids=v_devModelParamIds.split(',');
    //获取数据列表中的所有数据
    var rows = $("#mainTab").datagrid("getRows");
    //循环数据找出列表中ID和需要选中数据的ID相等的数据并选中
    for(var i=0;i<rows.length;i++){
      var rowId = rows[i].id;
      for(var j=0;j<ids.length;j++){
        if(rowId==ids[j]){
          var index = $("#mainTab").datagrid("getRowIndex",rows[i])
          $("#mainTab").datagrid("checkRow",index);
        }
      }
    }
  }




//选择列表行触发函数
function selectDevModelParms(rowIndex,rowData){	
	var selects =$('#mainTab').datagrid("getSelections");
	var v_devModelParamIds="";
	var paramName = "";
	for (var i = 0; i < selects.length; i++) {
		v_devModelParamIds += ","+selects[i].id;
		paramName += ","+selects[i].paramName;
	}
	$('#devModelParamIds').val(v_devModelParamIds.substr(1));
	paramName = "参数："+paramName.substring(1);
	$("#devmodeldescription").val(paramName);
	
}




//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}


//刷新父级页面数据
 function refreshParentData(){
		window.parent.refreshMyData();
 }





 function mysave(){
	 var checkedData =$('#mainTab').datagrid("getChecked");
	 if(checkedData.length<1|| typeof(checkedData)=="undefined"){
		$.messager.alert('系统提示','请至少选择一条参数！','info');
		return;
	 }
	 
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交中，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../deviceModelAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');	// hide progress bar while submit successfully
			data = JSON.parse(data);
			if (data.success){
				$.messager.alert('系统提示', '设备型号保存成功!','info',function(){
					closeParentModal();
					refreshParentData();
				});
			}else{
				$.messager.alert('异常', '设备型号保存失败!','error');
			}
		}
	});
}
 
 
 
 
 


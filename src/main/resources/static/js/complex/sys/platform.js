$(document).ready(function(){
	$('#devAddressCode').combotree({
		url:'../administrativeAction/getTreeBySystem',
		required: true,
		panelWidth:200,
		panelHeight:200,
		missingMessage:"该项必选",
		onLoadSuccess:function(){},
		onClick:function(node){},
	});
	$('#coordinate').combo({
		required:true,
		editable:true,
		panelWidth:400,
		panelHeight:200,
		onShowPanel:function(){
			$('#coordinateMapIframe').attr('src','/deviceInfoAction/goCoordinate');
		},
	});
	$('#coordinateMap').appendTo($('#coordinate').combo('panel'));
});

//把获取的坐标填入输入框中
function setLatLng(lng,lat){
	$('#coordinate').combo('setValue', lng+","+lat).combo('setText',  lng+","+lat).combo('hidePanel');
	$("#lng").val(lng);
	$("#lat").val(lat);
}
//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}
//重置表单
function clearForm(){
	$('#dataForm').form('clear');
}
//保存
function save(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	$('#dataForm').form('submit',{
		url:'../platformAction/save',
		onSubmit:function(param){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			var coordinate = $('#coordinate').val();
			param.lat = coordinate.split(",")[1];
			param.lng = coordinate.split(",")[0];
			return isValid;	
		},
		success: function(json){
			$.messager.progress('close');
			var data = JSON.parse(json);
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.location.reload();
				});
			}else{
				$.messager.alert('异常', '保存失败!','error');
			}
		}
	});
}



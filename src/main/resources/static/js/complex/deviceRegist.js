$(document).ready(function(){
	$('#devAddressCode').combotree({    
	    url:'../administrativeAction/getTreeBySystem', 
	    required: true,
		panelWidth:200,
		panelHeight:200,
	    missingMessage:"该项必选",
	    onLoadSuccess:function(){
	    	
	    },
	    onClick:function(node){
	    	// ====>限制只能注册到最低级
//	    	if(node.idLevel != "5"){
//	    		$.messager.alert("提示","请选择最低一级区域注册","info");
//	    		
//	    		$('#devAddressCode').combotree('setValue','');
//	    	}
	    	
	    	
	    	
	    	
//	    	var nodes = $('#devAddressCode').combotree('getParent',node.target);
//	    	console.log(nodes);
	    },
	}); 
	
	
	$('#coordinate').combo({
		required:true,
		editable:true,
		panelWidth:400,
		panelHeight:200,
		onShowPanel:function(){
			$('#coordinateMapIframe').attr('src','/deviceInfoAction/goMap');
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



//表单重置
function clearForm(){
	$('#dataForm').form('clear');
}




//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}



//注册
function baseSave(){
	 $.messager.progress({
        title : '系统提示',
        text : '信息提交中，请稍后。。。',
        interval:300
    });
	 
 	var v_devAddress = $('#devAddressCode').combotree('getText');
 	$("#devAddress").val(v_devAddress);
	 
	//var data =  $('#dataForm').serializeArray();
	$('#dataForm').form('submit',{
		url:'../backCommunicationAction/baseSave',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(json){
			$.messager.progress('close');	// hide progress bar while submit successfully
			var data = JSON.parse(json);
			console.log(data)
			if (data.success){
				$.messager.alert('系统提示', '注册成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.alert('异常', '注册失败!','error');
			}
		}
	});
}









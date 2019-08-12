//点击打开区域树获取注册地点
function getAddress(){
	$('#editIframe').attr('src','../deviceInfoAction/goTree');
	$('#eastDiv').panel('setTitle','点击获取设备注册地址');
}
//把区域树获取注册地点和编码填入数据框
function setAddress(address,code){
	code = code.substring(code.lastIndexOf("/")+1)
	$("#devAddress").textbox('setValue', address);
	$("#devAddressCode").val(code);
}
//点击打开地图获取坐标
function getCoordinate(){
	$('#editIframe').attr('src','../deviceInfoAction/goCoordinate');
	$('#eastDiv').panel('setTitle','点击获取设备安装坐标');
}
//把获取的坐标填入输入框中
function setLatLng(lng,lat){
	$("#lng").val(lng);
	$("#lat").val(lat);
	$("#_easyui_textbox_input4").val(lng);
	$("#_easyui_textbox_input5").val(lat);
}
//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}
//表单重置
 function clearForm(){
 	$('#dataForm').form('clear');
 }
//基本信息修改
function baseUpdate(){
	 $.messager.progress({
       title : '系统提示',
       text : '信息提交中，请稍后。。。',
       interval:300
   });
	$('#dataForm').form('submit',{
		url:'../deviceInfoAction/save',
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
				$.messager.alert('系统提示', '修改成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.alert('异常', '修改失败!'+data.data,'error');
			}
		}
	});
}



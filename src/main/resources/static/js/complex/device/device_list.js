$(document).ready(function(){
		$('#mainTab').datagrid({   
			onBeforeLoad : function(param){  
				var page = param.page; //保存下值
				var rows = param.rows;
				delete param.rows; //删掉
				delete param.page; //删掉
				param.pageIndex = page; //这里就是重新命名了
				param.pageSize = rows; //这里就是重新命名了
				if(param.sort=="devModel"){
					param.sort="deviceModel.id";
				}
			},
			url:'../deviceInfoAction/list',
			autoRowHeight:false,
		    nowrap:true,
			pagination:true,
			pageList:[10,20,30,40,100],
			fitColumns:false,
			checkOnSelect:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[
			                {field:'ck',width:5,checkbox:true},
					        {field:'id',title:'设备id',width:50,hidden:true},
					        {field:'devname',title:'设备名称',width:120,align:'center',sortable:"true"}, 
					        {field:'devdsn',title:'设备序列号',width:120,align:'center',sortable:"true"}, 
						]],
		    columns:[[
		        {field:'onregister',title:'注册状态',width:80,align:'center',formatter:register},
		        {field:'status',title:'设备状态',width:80,align:'center',formatter:status},
		        {field:'statusScript',title:'状态描述',width:80,align:'center',hidden:"true"},
		        {field:'devaddress',title:'设备地址',width:150,align:'center',sortable:"true"}, 
		        {field:'devaddresscode',title:'地址编码',width:150,align:'center',sortable:"true"}, 
		        {field:'resouceCode',title:'资源编码',width:150,align:'center',sortable:"true"}, 
		        {field:'lng',title:'经度',width:150,align:'center'}, 
		        {field:'lat',title:'纬度',width:150,align:'center'},
		        {field:'deviceModel',title:'设备型号',width:120,align:'center',sortable:"true",
		        	formatter: function(value,row,index){
		        		var devmodel = row.deviceModel;
		        		if(devmodel){
		        			devmodel = row.deviceModel.devmodel;
		        			return devmodel;
		        		}else{
		        			return "";
		        		}
		        	}
		        },
		        {field:'createBy',title:'注册用户',width:120,align:'center',sortable:"true"}, 
		        {field:'createTime',title:'注册时间',width:120,align:'center',sortable:"true"}, 
		        {field:'updateBy',title:'修改用户',width:120,align:'center',sortable:"true"}, 
		        {field:'updateTime',title:'修改时间',width:120,align:'center',sortable:"true"}, 
		    ]]    
		});
});
//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		devDsn:value
	});
}
function register(value,row,index){
	if (value==1){
		return '<span style="color: green;">已注册</span>';
	} else {
		return '<span style="color: gray;">未注册</span>';
	}
}
//设备状态1：开机/运行正常2：关机/停止运行3：故障4：故障恢复 5：播发中
function status(value){
	if (value==1){
		return '<span style="color: green;">在线</span>';
	} else if (value==2){
        return '<span style="color: gray;">离线</span>';
    }else if (value==3){
        return '<span style="color: gray;">故障</span>';
    }else if (value==4){
        return '<span style="color: gray;">故障恢复</span>';
    }else if (value==5){
        return '<span style="color: gray;">播发中</span>';
    }
}

//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}

//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}

//页面刷新(当页)
function refreshPage(){
	 $('#mainTab').datagrid("load");
}
//（打开模态框）
function add(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一个未注册设备！','info');
		return;
	}
	var id=checkedData[0].id;
	var statu_onregister=checkedData[0].onregister;
	if(statu_onregister!=0){
		$.messager.alert('选择提示','请选择一个未注册设备！','info');
		return;
	}
	$('#editIframe').attr('src','../deviceInfoAction/toRegist?id='+id);
	$('#editModal').window('setTitle','设备信息注册');
	$('#editModal').window('open');
}
//（打开模态框）
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一个已注册设备！','info');
		return;
	}
	var id=checkedData[0].id;
	var statu_onregister=checkedData[0].onregister;
	if(statu_onregister!=1){
		$.messager.alert('选择提示','请选择一个已注册设备！','info');
		return;
	}
	$('#editIframe').attr('src','../deviceInfoAction/toEdit?id='+id);
	$('#editModal').window('setTitle','设备基本信息修改');
	$('#editModal').window('open');
}
//参数设置
function send(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一个已注册设备！','info');
		return;
	}
	var id=checkedData[0].id;
	var statu_onregister=checkedData[0].onregister;
	if(statu_onregister!=1){
		$.messager.alert('选择提示','请选择一个已注册设备！','info');
		return;
	}
	$('#editIframe').attr('src','../deviceInfoAction/update?id='+id);
	$('#editModal').window('setTitle','设备参数设置');
	$('#editModal').window('open');
}
//删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择一条信息！','info');
		return;
	}
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			deleteData(checkedData[0].id);
		}
	});
}
function deleteData(id){
	$.ajax({
	    type: "post",
	    data: {id:id},
	    dataType: "json",
	    url: "../deviceInfoAction/delete",
	    success: function (json) {
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',function(){
					refreshPage();
				});
			}else {
				$.messager.alert('删除提示','删除失败！'+json.data,'error');
			}
	    },
	    error: function (data) {
	    	$.messager.alert('系统提示','异常：'+data,'error')
	    }
	});
}
$(document).ready(function(){
		$('#mainTab').datagrid({   
			onBeforeLoad : function(param){  
				var page = param.page; //保存下值
				var rows = param.rows;
				delete param.rows; //删掉
				delete param.page; //删掉
				param.pageIndex = page; //这里就是重新命名了
				param.pageSize = rows; //这里就是重新命名了
				param.map = {flag:0};
//				if(param.sort=="devModel"){
//					param.sort="deviceModel.id";
//				}
			},
			url:'../emergencyInfoAction/queryEmerPlan',
		    autoRowHeight:false,
		    nowrap:true,
			pagination:true,
			pageList:[10,20,30,40,100],
			fitColumns:false,
			checkOnSelect:true,
			singleSelect:true,
			idField:'id',
			frozenColumns:[[
                {field:'id',title:'预案id',width:150,checkbox:true},
		        {field:'emergencyname',title:'预案名称',width:150,align:'center'}, 
		        {field:'content',title:'播发信息',width:150,align:'left',halign:'center'},
			]],
		    columns:[[
		        {field:'accidentTypeName',title:'事件类型',width:150,align:'center',
		        	formatter: function(value,row,index){
		        		return row.accidentType.name;
		        	}
		        },          
			    {field:'accidentLevelName',title:'事件等级',width:150,align:'center',
		        	formatter: function(value,row,index){
		        		return row.accidentLevel.level;
		        	}
			    },
		        {field:'displayMethodName',title:'展示方式',width:150,align:'center',
			    	formatter: function(value,row,index){
		        		return row.displayMethod.method
		        	}
			    }, 
		        {field:'displayLanguageName',title:'展示语言',width:150,align:'center',
			    	formatter: function(value,row,index){
		        		return row.displayLanguage.language;
		        	}
		        },
		        {field:'infoSourceName',title:'输出资源',width:150,align:'center',
		        	formatter: function(value,row,index){
		        		return row.infoSource.infoSourceName;
		        	}
		        }, 
		        {field:'duration',title:'持续时间',width:150,align:'center',formatter:durationFun},
		        {field:'sound',title:'播发音量',width:150,align:'center',formatter:soundFun},
		        {field:'programdescription',title:'输入资源',width:150,align:'center'},
		        {field:'emergencycode',title:'预案编码',width:150,align:'center'}, 
		        {field:'createBy',title:'创建用户',width:150,align:'center',sortable:"true"},
		        {field:'createTime',title:'创建时间',width:150,align:'center',sortable:"true"},
		        {field:'updateBy',title:'修改用户',width:150,align:'center',sortable:"true"},
		        {field:'updataTime',title:'修改时间',width:150,align:'center',sortable:"true"}, 
		    ]]    
		});
});


function durationFun(value,rowData,rowIndex){
	return value+" 分钟";
}
function soundFun(value,rowData,rowIndex){
	return value+" %";
}




//搜索
function doSearch(value){
	$('#mainTab').datagrid('load',{
		emergencyName:value
	});
}



////关闭模态框
//function closeMyModal(){
//	$('#editModal').window('close');
//}

//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}


//增加 （
function add(){
	window.location.href="../emergencyInfoAction/toEdit?type=plan"
}




//2应急预案修改
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择预案信息！','info');
		return;
	}
	var v_id=checkedData[0].id;
	window.location.href="../emergencyInfoAction/toEdit?type=plan&id="+v_id;
}






//3应急预案删除
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择预案信息！','info');
		return;
	}
	var v_id=checkedData[0].id;

	$.messager.confirm('系统提示', '删除后将无法恢复，请谨慎操作！', function(r){
		if (r){
		    $.post("../emergencyInfoAction/delete",{ids:v_id},function(json){
		    	if(json.success){
		    		 $.messager.alert('系统提示','删除成功！','info',function(){
		    			 refreshMyData();
		    		 });
		    	}else{
		    		 $.messager.alert('系统提示','系统异常，删除失败！','error');
		    	}
		    });
		}
	});
};






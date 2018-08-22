$(document).ready(function(){
		$('#mainTab').datagrid({   
			onBeforeLoad : function(param){  
				var page = param.page; //保存下值
				var rows = param.rows;
				delete param.rows; //删掉
				delete param.page; //删掉
				param.pageIndex = page; //这里就是重新命名了
				param.pageSize = rows; //这里就是重新命名了
			},
			url:'../emergencyInfoAction/list',
			queryParams: {
				emergencyName: '',
			},
		    autoRowHeight:false,
		    nowrap:true,
			pagination:true,
			pageList:[10,20,30,40,100],
			fitColumns:false,
			checkOnSelect:true,
			singleSelect:false,
			idField:'id',
			frozenColumns:[[
                {field:'id',title:'预案id',width:150,checkbox:true},
		        {field:'emergencyname',title:'名称',width:150,align:'center'}, 
		        {field:'status',title:'状态',width:150,align:'center',formatter:statusFormatter}, 
			]],
		    columns:[[
		        {field:'startTime',title:'开始时间',width:150,align:'center'},
		        {field:'endTime',title:'结束时间',width:150,align:'center'},
				{field:'duration',title:'持续时间',width:150,align:'center',formatter:durationFun},     
				{field:'content',title:'播发信息',width:150,align:'left',halign:'center'},
		        {field:'programdescription',title:'输入资源',width:150,align:'center'},
		        {field:'infoSourceName',title:'输出资源',width:150,align:'center',
		        	formatter: function(value,row,index){
		        		return row.infoSource.infoSourceName;
		        	}
		        }, 
		        {field:'accidentType',title:'事件类型',width:150,align:'center',
		        	formatter: function(value,row,index){
		        		return row.accidentType.name;
		        	}
		        }, 
			    {field:'accidentLevel',title:'事件等级',width:150,align:'center',
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
		        {field:'endTime',title:'结束时间',width:150,align:'center'},
		        {field:'sound',title:'播发音量',width:150,align:'center',formatter:soundFun},
		        {field:'emergencycode',title:'信息编码',width:150,align:'center'},
		        {field:'ebmId',title:'播发编码',width:150,align:'center'},
		        {field:'addresscodename',title:'覆盖范围',width:150,align:'center'}, 

		        {field:'createBy',title:'创建用户',width:150,align:'center',sortable:"true"},
		        {field:'createTime',title:'创建时间',width:150,align:'center',sortable:"true"},
		    ]]    
		});
});

function statusFormatter(value,rowData,rowIndex){
	if(value=="2"){
		return '<font style="color: #FF9800;">待审核</font>';
	}else if(value == "3"){
		return '<font style="color:green;">未通过审核</font>';
	}else if(value == "4"){
		return '<font style="color: green;">已审核</font>';
	}else if(value=="5"){
		return '<font style="color: blue;">待发送</font>';
	}else if(value=="6"){
		return '<font style="color: green;">已发送</font>';
	}else if(value == "7"){
		return '<font style="color: green;">发送成功</font>';
	}else if(value=="8"){
		return '<font style="color: yellow;">等待播发</font>';
	}else if(value=="9"){
		return '<font style="color: green;">正在播发</font>';
	}else if(value == "10"){
		return '<font style="color: red;">播发失败</font>';
	}else if(value == "11"){
		return '<font style="color: #4EAF51">播发结束</font>';
	}
}


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


//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}


//关闭模态框
function closeMyModal(){
	$('#editModal').window('close');
}


//页面刷新(当前页)
function refreshPage(){
	 $('#mainTab').datagrid("reload");
}





//增加
function add(){
	window.location.href="../emergencyInfoAction/toEdit?type=add"
}






/**
 * 删除应急信息
 */
function remove(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length == 0 || typeof(checkedData) == "undefined"){
		$.messager.alert('选择提示','请选择一条信息！','info');
		return;
	}
	$.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
		if (r){
			var arr = new Array(checkedData.length);
			for(i in checkedData){
				if(checkedData[i].status == "9"){
					$.messager.alert("删除提示",checkedData[i].emergencyName+" 正在播发，无法删除！");
					return;
				}
				arr[i] = checkedData[i].id;
			}
			deleteData(arr);
		}
	});
}


function deleteData(ids){
	$.ajax({
	    type: "post",
	    data: {ids:ids},
	    url: "../emergencyInfoAction/delete",
	    traditional:true,
	    dataType:"json",
	    success: function (json) {
			if(json.success){
				$.messager.alert('删除提示','删除成功！','info',
					function(){
						refreshPage();
				});
			}
			else {$.messager.alert('删除提示','删除失败！','error');}
	    },
	    error: function (json) {
	    	$.messager.alert('系统提示','异常：'+json.data,'error')
	    }
	});
}
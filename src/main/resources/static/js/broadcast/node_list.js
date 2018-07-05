var rate = 1000;//心跳默认间隔，启动后由后台配置文件控制
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
			url:'../nodeAction/queryNode',
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
                {field:'id',title:'节点id',width:150,checkbox:true},
		        {field:'name',title:'名称',width:150,align:'center'}, 
		        {field:'nodeStatus',title:'节点状态',width:150,align:'center',formatter:nodeStatusFormatter}, 
			]],
		    columns:[[//ebdId
		    	{field:'url',title:'ip地址',width:150,align:'left',halign:'center'},
		    	{field:'linkStatus',title:'节点连接状态',width:150,align:'center',formatter:linkStatusFormatter},     
		        {field:'srcEbrid',title:'发送方EBRID',width:150,align:'center'},
		        {field:'destEbrid',title:'接收方EBRID',width:150,align:'center'},
		        {field:'ebdId',title:'节点ebdId',width:150,align:'center'},
		        {field:'createBy',title:'创建人',width:150,align:'center',sortable:"true"},
		        {field:'createTime',title:'创建时间',width:150,align:'center',sortable:"true"},
		        {field:'updateBy',title:'修改人',width:150,align:'center',sortable:"true"},
		        {field:'updateTime',title:'修改时间',width:150,align:'center',sortable:"true"},
		    ]],
		    onLoadSuccess:function(data){
               var id = setTimeout(() => {
		    		checkNode(data);
				}, rate);
		    }
		});
});

function nodeStatusFormatter(value,rowData,rowIndex){
	if(value=="0"){
		return '<font style="color: #FF9800;">停用</font>';
	}else if(value == "1"){
		return '<font style="color:green;">启用</font>';
	}
}


function linkStatusFormatter(value,rowData,rowIndex){
	if(value>=200&&value<300){
		return '<font style="color: green;">连接成功</font>';
	}else if(value==-2){
		return '<font style="color: #FF9800;">连接超时</font>';
	}else if(value==null){
		var msg = "测试中...";
		if(rowData.nodeStatus==0){
			msg = "节点未启用";
		}
		return '<font style="color: red;">'+msg+'</font>';
	}else{
		return '<font style="color:red;">连接失败!错误代码:'+value+'</font>';
	}
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
	$('#editIframe').attr('src','../nodeAction/toAdd');
	$('#editModal').window('setTitle','添加节点');
	$('#editModal').window('open');
}


//修改
function edit(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择节点！','info');
		return;
	}
	var v_id=checkedData[0].id;
	$('#editIframe').attr('src','../nodeAction/toAdd?id='+v_id);
	$('#editModal').window('setTitle','修改节点');
	$('#editModal').window('open');
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
	    url: "../nodeAction/delete",
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

/**********************WebScoket开始****************************/
var socket;
//是否连接
var isconn;
$(function(){
	if(typeof(WebSocket) == "undefined") {  
	    console.log("您的浏览器不支持WebSocket");  
	}else{  
	    console.log("您的浏览器支持WebSocket");  
	}
})
socket = new WebSocket("ws://localhost:8090/webscoket"); 
//打开事件  
socket.onopen = function() {  
	isconn = true;
};  
//获得消息事件  
socket.onmessage = function(msg) { 
	var data = msg.data;
	if(data=="连接成功"){
		return;
	}
	var nodes = JSON.parse(msg.data)
    $('#mainTab').datagrid("loadData",nodes)
};  
//关闭事件  
socket.onclose = function() {  
    //console.log("Socket已关闭");  
	isconn = false;
};  
//发生了错误事件  
socket.onerror = function() {  
   // alert("Socket发生了错误"); 
	isconn = false;
}  
$(window).unload(function(){  
      socket.close();
      isconn = false;
});

/***************************WebScoket完毕************************************/
function checkNode(data){
	var nodes = data.rows;
	$.ajax({
	    type: "post",
	    url: "../nodeAction/checkNode",
	    data:JSON.stringify(nodes),
	    contentType:"application/json",
	    traditional:true,
	    dataType:"json",
	    success: function (json) {
	    	if(json.success){
	    		nodes = json.data;
	    		$('#mainTab').datagrid("loadData",nodes)
	    	}
	    }
	});
}
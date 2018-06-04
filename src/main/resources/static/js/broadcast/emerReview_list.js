

$(function(){
	
	$('#mainTab').datagrid({   
		onBeforeLoad : function(param){  
			var page = param.page; // 保存下值
			var rows = param.rows;
			delete param.rows; // 删掉
			delete param.page; // 删掉
			param.pageIndex = page; // 这里就是重新命名了
			param.pageSize = rows; // 这里就是重新命名了
		},
		url:'../emergencyInfoAction/queryReviewList',
		queryParams:{queryParam:"emergency_info"},
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
		                {field:'startTime',title:'开始时间',width:150,align:'center'},
		                {field:'endTime',title:'结束时间',width:150,align:'center'},
		                {field:'duration',title:'持续时间',width:150,align:'center',formatter:durationFun},
		                ]],
		                columns:[[
		                          {field:'content',title:'播发信息',width:150,align:'left',halign:'center'},
		                          {field:'programdescription',title:'输入资源',width:150,align:'center'},
		                          {field:'infoSourceName',title:'输出资源',width:150,align:'center',
		                        	formatter: function(value,row,index){
		          		        		return row.infoSource.infoSourceName
		          		        	}  
		                          }, 
		                          {field:'accidentTypeName',title:'事件类型',width:150,align:'center',
		                        	  formatter: function(value,row,index){
		          		        		return row.accidentType.name
		          		        	}  
		                          }, 
		          			      {field:'accidentLevel',title:'事件等级',width:150,align:'center',
		          			    	formatter: function(value,row,index){
		          		        		return row.accidentLevel.level
		          		        	}
		          			      },
		          		          {field:'displayMethodName',title:'展示方式',width:150,align:'center',
		          			    	formatter: function(value,row,index){
		          		        		return row.displayMethod.method
		          		        	}
		          		          },
		          		          {field:'displayLanguageName',title:'展示语言',width:150,align:'center',
		          				    	formatter: function(value,row,index){
		          			        		return row.displayLanguage.language
		          			        	}
		          		          },
		                          {field:'endTime',title:'结束时间',width:150,align:'center'},
		                          {field:'sound',title:'播发音量',width:150,align:'center',formatter:soundFun},
		                          {field:'emergencycode',title:'信息编码',width:150,align:'center'}, 
		                          {field:'createBy',title:'创建用户',width:150,align:'center',sortable:"true"},
		                          {field:'createTime',title:'创建时间',width:150,align:'center',sortable:"true"},
		                          ]]     
		});
	
	
	
	
	/**
	 * 审核类型 选择事件
	 */
	$("#type_select").combobox({
		onChange:function(data) {   
			var queryParams = $('#mainTab').datagrid('options').queryParams;
			if(data == 2){
				queryParams.queryParam = 'emergency_plan';
				$("#mainTab").datagrid("reload");
			}else{
				queryParams.queryParam = "emergency_info"
				$("#mainTab").datagrid("reload");
			}
	　　  }  
	});
	
});


function durationFun(value,rowData,rowIndex){
	return value+" 分钟";
}
function soundFun(value,rowData,rowIndex){
	return value+" %";
}





window.top["reload_review_list"] = function(){
	$('#mainTab').datagrid("load");
}


// 页面刷新
function refreshMyData(){
	$('#mainTab').datagrid("load");
}


// 审核
function review(){
	var checkedData =$('#mainTab').datagrid("getChecked");
	if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
		$.messager.alert('选择提示','请选择信息！','info');
		return;
	}
	var v_id=checkedData[0].id;
	console.log(checkedData);

	var v_flag=checkedData[0].flag;
	// 预案->设置播发时间
	if(v_flag=="0"){
		// 预案->设置播发时间
		var nowTime = new Date().Format("yyyy-MM-dd HH:mm:ss");
		console.log(nowTime);
		
		var v_name = checkedData[0].emergencyName + '(' + nowTime+')';
		$("#emer_name").textbox("setValue",v_name);
		$("#start_time").datetimebox("setValue",nowTime);
		$('#set_time_dialog').dialog({
			iconCls:"icon-edit",
			closed: false,
			cache: false,
			buttons: [{
				text:'确定',
				iconCls: 'icon-ok',
				handler: function () {
					v_name = $("#emer_name").val();
					v_time = $("#start_time").val();
					$.post("../emergencyInfoAction/review",{id:v_id, starttime:v_time, emerName:v_name},function(data){
						if(data=="ok"){
							$.messager.alert('系统提示','审核成功！','info',function(){
								$('#set_time_dialog').dialog("close");
								refreshMyData();
								window.parent.refreshTabData("信息播发",window.top.reload_broadcast_list);
							});
						}else{
							$.messager.alert('系统提示','系统异常，审核失败！','error',function(){
								
							});
						}
					});
				}
			}, {
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function () {
					$('#set_time_dialog').dialog('close');
				}
			}]

		});
	}else{
		$.messager.confirm('审核提示', '是否通过审核？', function(r){
			if (r){
				$.post("../emergencyInfoAction/review",{id:v_id},function(data){
					if(data=="ok"){
						$.messager.alert('系统提示','审核成功！','info',function(){
							refreshMyData();
							window.parent.refreshTabData("信息播发",window.top.reload_broadcast_list);
						});
					}else{
						$.messager.alert('系统提示','系统异常，审核失败！','error');
					}
				});
			}
		});
	}

}





/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 例子： (new
 * Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 * 
 * @param fmt
 * @returns
 */ 
Date.prototype.Format = function (fmt) { // author: meizz
	var o = {
			"M+": this.getMonth() + 1, // 月份
			"d+": this.getDate(), // 日
			"H+": this.getHours(), // 小时
			"m+": this.getMinutes(), // 分
			"s+": this.getSeconds(), // 秒
			"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
			"S": this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}




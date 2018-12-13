$(document).ready(function(){
	intiEmerTab();
	intiMap();
});


//获取初始化地图所需要的数据然后初始化地图 unitLng,unitLat,level,mapStyle
function intiMap(){
	var unit_jsonString=window.parent.getUnitMessage();
	var unit_json=jQuery.parseJSON(unit_jsonString);
	var unitLng=unit_json.longitude;
	var unitLat=unit_json.latitude;
	setMyMap(unitLng,unitLat,12,"light");
}


//初始化获取所有已经注册的设备集合json
function getDevByEmerAreCode(addressCodes){
	$.post("../deviceInfoAction/findByCodes",{code:addressCodes},function(json){
		var addressJsonArray=json.data
	    map.clearOverlays();//清除地图覆盖物       
		for (var i= 0; i < addressJsonArray.length; i++) {
			getBoundary(addressJsonArray[i]);
		}
		
		
		
//		var devJsonArray=data;
//		if(devJsonArray.length!=0){
//			var onNormal=[];//正常
//			var onWarning=[];//报警
//			var dropLine=[];//离线
//			for (var i = 0; i < devJsonArray.length; i++) {
//				var status = devJsonArray[i].status;
//				var	status7 = status.charAt(7);//注册位
//				var	status6 = status.charAt(6);//工作位（网管系统不管理是否工作）  => 在线位
//				var	status5 = status.charAt(5);//报警位   => 工作位
//				var	status4 = status.charAt(4);//在线位  => 报警位
//				if(status6==1&&status4==0&&status7==1){//正常
//					onNormal.push(devJsonArray[i]);
//				}
//				if(status6==1&&status4==1&&status7==1){//报警
//					onWarning.push(devJsonArray[i]);
//				}	
//				if(status6==0&&status7==1){//离线
//					dropLine.push(devJsonArray[i]);
//				}
//			}
//			initMarks(onNormal,onWarning,dropLine);//初始化地图标注
//		}
	},'json');
}

/**
 * 审核成功 调用此函数 属性播发界面数据
 */
window.top["reload_broadcast_list"] = function(){
	$('#mainTab').datagrid("load");
}






//========================================================发送和停止发送==================
//发送
function send(){
	var selectData =$('#mainTab').datagrid("getSelected");
	if(selectData==null){
		$.messager.alert('系统提示','请选择待发送的信息！','info');
		return;
	}
	var v_statu=selectData.status;
	var v_id=selectData.id;
	if(v_statu!="5"){
		$.messager.alert('系统提示','请选择待发送的信息！','info');
		return;
	}

	 $.messager.progress({
         title : '系统提示',
         text : '指令发送中，请稍后。。。',
         interval:300
     });
	 $.post("../emergencyInfoAction/sendEmer",{emerId:v_id},function(data){
			$.messager.progress('close');
	    	if(data.Result=="success"){
	    		 $.messager.alert('系统提示','发送成功！','info',function(){
	    			 $('#mainTab').datagrid("load");
	    			 //发送成功后将按钮 -> 停止发送
	    			 var newElement = '<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="stopSend();">停止发送</a>';
	    			 $("#ctrl_btn_div").html(newElement);
	    			 $.parser.parse($("#ctrl_btn_div"));
	    			 
	    			 
	    		 });
	    	}else{
	    		 $.messager.alert('系统提示','发送失败,'+data.Err,'error');
	    	}
	    },'json').error(function(data){
	    	$.messager.progress('close');
	    	$.messager.alert('系统提示','发送失败,','error');
	    });
}



function stopSend(){
	var selectData =$('#mainTab').datagrid("getSelected");
	if(selectData==null){
		$.messager.alert('系统提示','请选择已发送的信息！','info');
		return;
	}
	var v_statu=selectData.status;
	var v_id=selectData.id;
	if(v_statu!="6"){
		$.messager.alert('系统提示','请选择已发送的信息！','info');
		return;
	}
	 $.messager.progress({
         title : '系统提示',
         text : '指令发送中，请稍后。。。',
         interval:300
     });
	 $.ajax({
		 url:"../backCommunicationAction/emerStopMessage",
		 type:"post",
		 dataType:"json",
		 data:{emerId:v_id},
		 success:function(json){
			 $.messager.progress('close');
			 if (json.success) {
				$.messager.alert('系统提示', '停止成功！', 'info', function() {
					$('#mainTab').datagrid("load");
					$("#ctrl_btn_div").html("");
				});
			} else {
				$.messager.alert('系统提示', '停止失败', 'error');
			}
		 },error:function(json){
			 $.messager.progress('close');
			 $.messager.alert('系统提示', '网络异常，请稍后再试!', 'error');
		 }
	 });
}





//==============================tab======================================
function intiEmerTab(){
	$('#mainTab').datagrid({   
		onBeforeLoad : function(param){  
			var page = param.page; //保存下值
			var rows = param.rows;
			delete param.rows; //删掉
			delete param.page; //删掉
			param.pageIndex = page; //这里就是重新命名了
			param.pageSize = rows; //这里就是重新命名了
		},
		onSelect:function(rowIndex,rowData){//选择表格行触发
			getEmertail(rowIndex,rowData);
		},
		url:'../emergencyInfoAction/castList',
		autoRowHeight:false,
	    nowrap:true,
		fitColumns:true,
		checkOnSelect:false,
		singleSelect:true,
		pagination:true,
		idField:'id',
		pageSize:20,
	    columns:[[
		 	{field:'id',title:'信息id',width:50,hidden:"true"},
	        {field:'emergencyname',title:'信息名称',width:60,align:'center'}, 
	        {field:'status',title:'发送状态',width:30,align:'center',sortable:'true',formatter:statusFormatter}
	    ]]
	});
}

function statusFormatter(value,rowData,rowIndex){
	if(value=="5"){
		return '<font style="color: green;">待发送</font>';
	}
	if(value=="6"){
		return '<font style="color: red;">已发送</font>';
	}
}



function getEmertail(rowIndex,rowData){
	$('#myLayOut2').layout('panel', 'east').panel({ title:'信息：'+rowData.emergencyname });
	$('#myLayOut2').layout('expand', 'east');
	$("#emerId").html(rowData.id);
	$("#emergencyCode").html(rowData.emergencycode);
	$("#start_time").html(rowData.startTime);
	$("#end_time").html(rowData.endTime);
	$("#duration").html(rowData.duration+"分钟");
	$("#sound").html(rowData.sound+"%");
	$("#programDescription").html(rowData.programdescription);
	$("#infoSourceName").html(rowData.infoSource.infoSourceName);
	$("#accidentTypeName").html(rowData.accidentType.name);
	$("#accidentLevelName").html(rowData.accidentLevel.level);
	$("#displayMethodName").html(rowData.displayMethod.method);
	$("#displayLanguageName").html(rowData.displayLanguage.language);
	$("#dispalyText").html(rowData.content);
	$("#addressCode").html(rowData.addresscode);
	$("#EBM_ID").html(rowData.ebmId);
	if(rowData.status == "5"){
		$("#ctrl_btn_div").html('<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="send();">信息发送</a>')
	}else if(rowData.status == "6"){
		$("#ctrl_btn_div").html('<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="stopSend();">停止发送</a>')
	}
	$.parser.parse($("#ctrl_btn_div")); 
	getDevByEmerAreCode(rowData.addresscode);

}



//页面刷新
function refreshMyData(){
	 $('#mainTab').datagrid("load");
}

/*************map.js***********/
//在Icon对象的原型中添加属性和方法，用来标记对应的图标,用这个在遍历对象的时候获取到对应的覆盖物
BMap.Icon.prototype.statu = "";
BMap.Icon.prototype.setStatu = function(statu){
    this.statu = statu;
}

BMap.Icon.prototype.devDsn = "";
BMap.Icon.prototype.setDevDsn = function(devDsn){
    this.devDsn = devDsn;
}   



//初始化地图
function setMyMap(unitLng,unitLat,level,mapStyle){
//	var map = new BMap.Map("main_map"); // 创建Map实例
	var point = new BMap.Point(unitLng,unitLat);//创建点坐标
	map.centerAndZoom(point,level);//初始化地图,设置中心点坐标和地图级别      
	//1添加小地图
	var overView = new BMap.OverviewMapControl();
	var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});
	map.addControl(overView);          //添加默认缩略地图控件
	//map.addControl(overViewOpen);      //右下角，打开
	//2添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		anchor: BMAP_ANCHOR_TOP_RIGHT,
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	//map.setCurrentCity("赣州市");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.setMapStyle({style:mapStyle});
}


//4创建控件 添加到地图当中
function addControl(newControl){
	var myZoomCtrl = newControl;
	map.addControl(myZoomCtrl);
}




//初始地图标注
function initMarks(onNormal,onWarning,dropLine){
	var locationIcon= '../common/image/chartsIcon/location.png'
	var normalIcon= '../common/image/chartsIcon/green.png'
	var dropIcon= '../common/image/chartsIcon/gray.png'
	var warningIcon= '../common/image/chartsIcon/red.png'
	
	map.clearOverlays(); //清除覆盖物        
	
		
	if(onNormal.length!=0&&onNormal!=null){
		for (var i = 0; i < onNormal.length; i++) {
			var array=onNormal[i];
			addMyMarks(array,normalIcon,"normal");
		}
	}	
	if(onWarning.length!=0&&onWarning!=null){
		for (var i = 0; i < onWarning.length; i++) {
			var array=onWarning[i];
			addMyMarks(array,warningIcon,"warning");
		}
	}	
	if(dropLine.length!=0&&dropLine!=null){
		for (var i = 0; i < dropLine.length; i++) {
			var array=dropLine[i];
			addMyMarks(array,dropIcon,"drop");
		}
	}	
}





//在地图中添加标注
function addMyMarks(deviceJson,iconUrl,statuName){

	var myPoint = new BMap.Point(deviceJson.lng,deviceJson.lat);
	var myIcon = new BMap.Icon(iconUrl, new BMap.Size(40, 40));
	myIcon.setStatu(statuName);//对这个图标设定它的statu属性值为 normal warning  drop；
	myIcon.setDevDsn(deviceJson.devDsn);
	var marker = new BMap.Marker(myPoint, {icon: myIcon}); //创建标注对象并添加到地图   
	marker.setTitle(deviceJson.devName);
	
	$("#windowDevName").html(deviceJson.devName);
	$("#windowDevId").html(deviceJson.id);
	$("#windowDevDsn").html(deviceJson.devDsn);
	$("#windowDevStatus").html(deviceJson.devName);
	$("#windowDevStatusScript").html(deviceJson.statusScript);
	$("#windowDevAddress").html(deviceJson.devAddress);
	
	if(statuName=="normal"){
		$("#windowDevStatus").html("正常");
	}
	if(statuName=="warning"){
		//设置标注动画效果。如果参数为null，则取消动画效果。该方法需要在addOverlay方法后设置
		//marker.setAnimation(BMAP_ANIMATION_BOUNCE);
		$("#windowDevStatus").html("报警");
	}
	if(statuName=="drop"){
		$("#windowDevStatus").html("离线");
	}
	
	var myWindowContent = $("#mapWindowContent").html();
	var infoWindow = new BMap.InfoWindow(myWindowContent); // 创建信息窗口对象
	marker.addEventListener("click", function(){          
		 this.openInfoWindow(infoWindow);
	});
	map.addOverlay(marker);	
}






//窗口面板上面的更多功能方法
function getDevDetail(){
	alert("功能暂时未开放");
}






//隐藏指定状态标注
function hideMyMarks(statusName){
	 var allmap = map.getOverlays();
     var map_length = allmap.length;
     for(var i = 0; i < map_length; i ++){
         if(allmap[i].toString() == "[object Marker]"){
             //console.log(i);
             if(allmap[i].getIcon().statu == statusName){
                 allmap[i].hide()
             }
         }
     }
}

//显示指定状态的标注
function showMyMarks(statusName){
	 var allmap = map.getOverlays();
    var map_length = allmap.length;
    for(var i = 0; i < map_length; i ++){
        if(allmap[i].toString() == "[object Marker]"){
            //console.log(i);
            if(allmap[i].getIcon().statu == statusName){
                allmap[i].show()
            }
        }
    }
}






function getBoundary(name){       
    var bdary = new BMap.Boundary();
    bdary.get(name, function(rs){   //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个
        for(var i = 0; i < count; i++){
            var ply = new BMap.Polygon(rs.boundaries[i], 
                      {strokeWeight: 1, //设置多边形边线线粗
                       strokeOpacity:0, //设置多边形边线透明度0-1
                       StrokeStyle: "solid", //设置多边形边线样式为实线或虚线，取值 solid 或 dashed
                       strokeColor: "#ff0000", //设置多边形边线颜色
                                       }); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            map.setViewport(ply.getPath());    //调整视野         
        }                
    });   
}









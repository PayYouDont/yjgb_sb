var emerArrayJson;// 已发布信息
var app = {};
$(function() {
	// 解决ajax的异步问题
	$.ajaxSetup({
		async : false
	});
	initEmerInfo();
	initMap();

	// getEmerByBrodcast();//1获取已发布的应急信息
	// forEmerNum();//轮询播发数量
	// initEmer();//2初始化应急信息
	// init_EmerNum();//3初始化应急信息数量图
	// getChartsParm("");//4初始化 echarts
	// initWebSocket();//5初始化websocket

});

/**
 * 获取已经发布了的应急信息
 */
function getEmerByBrodcast() {
	$.post("../emergencyInfoAction/getEmerByBrodcast", function(data) {
		emerArrayJson = jQuery.parseJSON(data);
	});
}

// 1获取已发布的应急信息集合json并且初始化应急信息选择下拉面板
function initEmer() {
	$("#emerDemo").collapse('hide');
	for (var i = 0; i < emerArrayJson.length; i++) {
		var starttime = formatLongDate(emerArrayJson[i].start_time.time);
		// 1初始化应急信息
		var htmlOption = '<div class="emerDiv" id="' + emerArrayJson[i].id
				+ '" onclick="selectEmer(this)">' + '<div >'
				+ '<span class="badge badge-success">急</span>'
				+ '	<font id="divEmerName" class="emerTitle">'
				+ emerArrayJson[i].emergencyName + '</font>' + '</div>'
				+ '<div>' + '	<font class="emerFont1">等级：</font>'
				+ '	<font id="divLevelName" class="emerFont2">'
				+ emerArrayJson[i].accidentLevelName + '</font>' + '</div>'
				+ '<div>' + '	<font class="emerFont1">类型：</font>'
				+ '	<font id="divTypeName" class="emerFont2">'
				+ emerArrayJson[i].accidentTypeName + '</font>' + '</div>'
				+ '<div>' + '	<font class="emerFont1">开始：</font>'
				+ '	<font  id="divStarttime" class="emerFont2">' + starttime
				+ '</font>' + '</div>' + '</div>';
		$("#emerDemo").append(htmlOption);
	}
}

/**
 * 轮询播发数量
 */
function forEmerNum() {
	setInterval(init_EmerNum, 2000);
}

function init_EmerNum() {
	var emerCast = [];
	var emerCastNot = [];
	for (var i = 0; i < emerArrayJson.length; i++) {
		var emerStarttime = emerArrayJson[i].start_time.time;
		var nowTime = (new Date()).getTime();
		if (emerStarttime > nowTime) {
			emerCastNot.push(emerArrayJson[i]);
		} else {
			emerCast.push(emerArrayJson[i]);
		}
	}
	$("#workNum").html(emerCast.length);
	$("#waitNum").html(emerCastNot.length);
}

// 应急信息选择
function selectEmer(e) {
	$(e).parent().children().attr("class", "emerDiv");
	$(e).attr("class", "emerDivActive");
	var emerId = $(e).attr("id");

	if (emerId == "" || typeof (emerId) == "undefined") {
		$("#emerTitle").css("display", "none");
		$("#emerId").val("");

	} else {
		$("#emerTitle").css("display", "block");

		var emer_Name = $(e).children().find("#divEmerName").html();
		var emer_Level = $(e).children().find("#divLevelName").html();
		var emer_Type = $(e).children().find("#divTypeName").html();
		var emer_starttime = $(e).children().find("#divStarttime").html();
		$("#emerId").val(emerId);
		$("#emerName").html(emer_Name);
		$("#emerType").html(emer_Type);
		$("#emerLevel").html(emer_Level);
		// $("#emerDuration").html();
		$("#emerStarttime").html(emer_starttime);
	}
	getChartsParm(emerId);
}

// 获取bar图所需要的动态数据
function getChartsParm(emerId) {
	$.post('../emergencyInfoAction/getDeviceByEmerId', {
		id : emerId
	}, function(data) {
		initBar(data);
		initPie(data);
		initMap(data);
	}, 'json');
}

// 获取平台信息
function getLocation() {
	var locationData;
	$.post("../emergencyInfoAction/getLocation", function(data) {
		locationData = jQuery.parseJSON(data);
	});
	return locationData;
}

// 获取所有已经注册了的设备集合json
function getDeListByRe() {
	var deviceAllJson;
	$.post("../deviceInfoAction/getDeListByRe", function(data) {
		deviceAllJson = jQuery.parseJSON(data);
	});
	return deviceAllJson;
}

function initEmerInfo() {
	$('#mainTab').datagrid({
		onBeforeLoad : function(param) {
			var page = param.page; // 保存下值
			var rows = param.rows;
			delete param.rows; // 删掉
			delete param.page; // 删掉
			param.pageIndex = page; // 这里就是重新命名了
			param.pageSize = rows; // 这里就是重新命名了
		},
		queryParams : {
		// devName:'',
		},
		url : '../emergencyInfoAction/queryBroadcastingEmer',
		autoRowHeight : false,
		nowrap : true,
		pagination : true,
		pageList : [ 10, 20, 30, 40, 100 ],
		fitColumns : false,
		checkOnSelect : false,
		singleSelect : true,
		onSelect : function(rowIndex, rowData) {// 选择表格行触发
			getDevByEmerAreaCode(rowData.addresscode);
		},
		idField : 'id',
		frozenColumns : [ [ {
			field : 'id',
			title : 'id',
			width : 50,
			hidden : true
		}, {
			field : 'emergencyname',
			title : '名称',
			width : 120,
			align : 'center',
			sortable : "true"
		}, ] ],
		columns : [ [ {
			field : 'accidentTypeName',
			title : '事件类型',
			width : 150,
			align : 'center',
			sortable : "true",
			formatter : function(value, row, index) {
				return row.accidentType.name
			}
		}, {
			field : 'accidentLevelName',
			title : '事件等级',
			width : 150,
			align : 'center',
			sortable : "true",
			formatter : function(value, row, index) {
				return row.accidentLevel.level
			}
		}, {
			field : 'startTime',
			title : '开始时间',
			width : 150,
			align : 'center',
			sortable : "true"
		}, {
			field : 'endTime',
			title : '结束时间',
			width : 150,
			align : 'center'
		}, {
			field : 'status',
			title : '状态',
			width : 80,
			align : 'center',
			sortable : "true",
			formatter : statusFormatter
		}, {
			field : 'sound',
			title : '地址编码',
			width : 150,
			align : 'center',
			hidden : true
		}, {
			field : 'lng',
			title : '经度',
			width : 150,
			align : 'center',
			hidden : true
		}, {
			field : 'lat',
			title : '纬度',
			width : 150,
			align : 'center',
			hidden : true
		}, ] ]
	});
}

function statusFormatter(value, row, index) {
	return '<font style="color: green;">正在播发</font>';
}

function doSearch(param) {
	$("#mainTab").datagrid("load", {
		searchText : param
	});
}

function getDevByEmerAreaCode(addressCodes) {
	$.post("../deviceInfoAction/findByCodes", {
		code : addressCodes
	}, function(data) {
		var addressJsonArray = data.data
		map.clearOverlays();// 清除地图覆盖物
		for (var i = 0; i < addressJsonArray.length; i++) {
			getBoundary(addressJsonArray[i]);
		}
	}, 'json');
}
/** *********map.js内容************** */
/**
 * 获得地图所需要的数据，然后初始化地图
 */
function initMap() {
	var unit_jsonString = window.parent.getUnitMessage();
	var unit_json = jQuery.parseJSON(unit_jsonString);
	var unitLng = unit_json.longitude;
	var unitLat = unit_json.latitude;
	setMap(unitLng, unitLat, 13, "light");
}
/**
 * 初始化地图
 * 
 * @param unitLng
 * @param unitLat
 * @param unit
 * @param level
 * @param style
 */
function setMap(unitLng, unitLat, level, myStyle) {
	var point = new BMap.Point(unitLng, unitLat);
	map.centerAndZoom(point, level);
	var overView = new BMap.OverviewMapControl();
	var overViewOpen = new BMap.OverviewMapControl({
		isOpen : true,
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT
	});
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	map.addControl(top_left_control);        
	map.addControl(top_left_navigation); 
	map.addControl(overView); // 添加默认缩略地图控件
	// map.addControl(overViewOpen); //右下角，打开
	// 2添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		anchor : BMAP_ANCHOR_TOP_RIGHT,
		mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
	}));
	// map.setCurrentCity("赣州市"); // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	map.setMapStyle({
		style : myStyle
	});
	//初始化app覆盖物属性
	initAppPolygon();
	//添加自定义覆盖物
	customOverlay();
	//创建导入菜单
	createBMapMenu();
}
/****************自定义覆盖物************************/
//初始化app覆盖物属性
function initAppPolygon(){
	app.Polygon = getPolygon();
}
function getPolygon(){
	return new BMap.Polygon([],{strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});
}
//定义一个控件类,即function
function ZoomControl(){
  // 默认停靠位置和偏移量
  this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
  this.defaultOffset = new BMap.Size(10, 10);
}
function customOverlay(){
	// 通过JavaScript的prototype属性继承于BMap.Control
	ZoomControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	ZoomControl.prototype.initialize = function(map) {
		// 创建一个DOM元素
		var div = document.createElement("div");
		// 添加文字说明
		div.appendChild(document.createTextNode("添加覆盖"));
		// 设置样式
		div.style.cursor = "pointer";
		div.style.border = "1px solid gray";
		div.style.backgroundColor = "white";
		var Listener;
		// 绑定事件,点击一次放大两级
		div.onclick = function(e) {
			var text = this.innerText;
			if(text=="添加覆盖"){
				map.setDefaultCursor("Default");   //设置地图默认的鼠标指针样式
				//单击获取点击的经纬度
				map.addEventListener("click",Listener = function(e){
					var lng = e.point.lng;
					var lat = e.point.lat;
					//添加覆盖物
					add_overlay(lng,lat);
				});
				this.innerText = "关闭添加";
				//添加鼠标右键
				markMenu(app.Polygon);
			}else{
				map.setDefaultCursor("Pointer");   //设置地图默认的鼠标指针样式
				if(Listener){
					map.removeEventListener("click",Listener);
					initAppPolygon();
				}
				this.innerText = "添加覆盖"
			}
			
		}
		// 添加DOM元素到地图中
		map.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}
	// 创建控件
	var myZoomCtrl = new ZoomControl();
	// 添加到地图当中
	map.addControl(myZoomCtrl);
}
// 添加覆盖物
function add_overlay(lng,lat){
	var Point = new BMap.Point(lng,lat);
	var pointArr = app.Polygon.getPath();
	pointArr.push(Point)
	app.Polygon.setPath(pointArr);
	app.Polygon.enableEditing();
	map.addOverlay(app.Polygon);
}
//清除覆盖物
var removePolygon = function(e,ee,polygon){
	map.removeOverlay(polygon);
	initAppPolygon();
	markMenu(app.Polygon);
}
//导出覆盖物数据
var exportPolygon = function(e,ee,polygon){
	var data = JSON.stringify(polygon.getPath());
	var urlObject = window.URL || window.webkitURL || window;
	var export_blob = new Blob([data]);
	var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
	save_link.href = urlObject.createObjectURL(export_blob); 
	save_link.download = new Date().getTime()+".txt";
	var ev = new MouseEvent("click", {
	    bubbles: true,
	    cancelable: true,
	    view: window
	});
	save_link.dispatchEvent(ev);
}
//创建右键菜单
function markMenu(polygon){
	var markerMenu=new BMap.ContextMenu();
	markerMenu.addItem(new BMap.MenuItem('删除',removePolygon.bind(polygon)));
	markerMenu.addItem(new BMap.MenuItem('导出',exportPolygon.bind(polygon)));
	map.addOverlay(polygon);
	polygon.addContextMenu(markerMenu);
}
//地图上创建导入菜单
function createBMapMenu(){
	var menu = new BMap.ContextMenu();
	var txtMenuItem = {
			text:'导入覆盖物',
			callback:function(){
				importPolygon();
			}
		};
	menu.addItem(new BMap.MenuItem(txtMenuItem.text,txtMenuItem.callback,100));
	map.addContextMenu(menu);
}
//导入覆覆盖物
function importPolygon(){
	var input = document.createElement("input");
	input.setAttribute("type", "file");
	input.setAttribute("style", "visiblity:hidden");
	document.body.appendChild(input);
	input.multiple = true;
	input.click();
	input.addEventListener("change", function() {
		var files = this.files;
		for(var i=0;f=files[i];i++){
			var reader = new FileReader();
			reader.readAsText(f, "UTF-8");// 读取文件
			reader.onload = function(evt) { // 读取完文件之后会回来这里
				var result = evt.target.result; // 读取文件内容
				try{
					var data = JSON.parse(result);
					add_overlayByData(data);
				}catch(e){
					console.log(e)
					alert("数据读取错误！");
				}
			}
		}
	});
}
//添加覆盖物
function add_overlayByData(data){
	if(!(data instanceof Array)){
		alert("导入的不是覆盖物数据");
		return;
	}
	var polygon = getPolygon();
	map.addOverlay(polygon);
	var pointArr = polygon.getPath();
	for(var i=0;i<data.length;i++){
		var lng = data[i].lng;
		var lat = data[i].lat;
		var point = new BMap.Point(lng,lat);
		pointArr.push(point);
	}
	polygon.setPath(pointArr);
	polygon.enableEditing();
	map.addOverlay(polygon);
	markMenu(polygon);
}
/*******************************************************************************
 * 获得区域边界，并覆盖
 * 
 * @param name
 */
function getBoundary(name) {
	var boundary = new BMap.Boundary();
	boundary.get(name, function(rs) {
		var count = rs.boundaries.length;
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {
				strokeWeight : 1, // 设置多边形边线线粗
				strokeOpacity : 0, // 设置多边形边线透明度0-1
				StrokeStyle : "solid", // 设置多边形边线样式为实线或虚线，取值 solid 或 dashed
				strokeColor : "#ff0000", // 设置多边形边线颜色
			}); //建立多边形覆盖物
			map.addOverlay(ply); //添加覆盖物
			map.setViewport(ply.getPath()); //调整视野         
		}
	});
}
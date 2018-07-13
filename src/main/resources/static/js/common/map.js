var emerArrayJson;// 已发布信息

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
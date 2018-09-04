
var app = {
	date:"week",
	getDate:function(){
		var startTime,endTime;
		if(this.date=="day"){
			startTime = new Date().format("yyyy-MM-dd hh:mm:ss");
			endTime = new Date().format("yyyy-MM-dd hh:mm:ss");
		}else if(this.date=="week"){
			var startDate = new Date().getTime()-7*24*60*60*1000;
			startTime = new Date(startDate).format("yyyy-MM-dd hh:mm:ss");
			endTime = new Date().format("yyyy-MM-dd hh:mm:ss");
		}else{
			var startDate = new Date().getTime()-30*24*60*60*1000;
			startTime = new Date(startDate).format("yyyy-MM-dd hh:mm:ss");
			endTime = new Date().format("yyyy-MM-dd hh:mm:ss");
		}	
		return {startTime:startTime,endTime:endTime}
	}
}
$(function() {
	initDate();
	initMap();
	initChart();
})
function initMap() {
	var myChart = echarts.init(document.getElementById('map'));
	var option = {
		bmap : {
			center : [ 116.73519, 23.513167 ], // 天河城
			zoom : 13,
			roam : true,
			mapStyle : {
				styleJson : styleJson
			}
		},
		series : [ {
			type : 'map',
			data : [ [ 120, 30, 1 ] ]
		}, ]

	}
	myChart.setOption(option);
	var bmap = myChart.getModel().getComponent('bmap').getBMap();
	bmap.addControl(new BMap.MapTypeControl());
}
function initDate(){
	$("#date").on("change",function(){
		var date = $("#date option:selected").val();
		app.date = date;
		initChart();
	})
}
function initChart() {
	var startTime = app.getDate().startTime;
	var endTime = app.getDate().endTime;
	var data  = {type:"count",startTime:startTime,endTime:endTime};
	getData(data,initCount)
	data.type = "type";
	getData(data,initType)
	data.type = "source";
	getData(data, initSource)
}
function getData(data,func){
	if(!func||typeof(func)!='function'){
		return;
	}
	$.ajax({
		url:"/statisticsAction/getData",
		dataType:"json",
		data:data,
		type:"post",
		success:function(json){
			if(json.success){
				var data = json.data;
				func(data);
			}
		}
	});
}
function initCount(data) {
	var myChart = echarts.init(document.getElementById('count-chart'));
	var option = {
		tooltip: {
	        trigger: 'axis'
	    },
		legend: {
	        data:['1级', '2级', '3级', '4级']
	    },
		xAxis : {
			type : 'category',
			data : [ '一', '二', '三', '四', '五', '六', '日' ]
		},
		yAxis : {
			type : 'value'
		},
		series : data
	}
	myChart.setOption(option);
}
function initType(data) {
	var myChart = echarts.init(document.getElementById('type-chart'));
	option = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		    	type:"scroll",
		        left: 'left',
		        data:data.legendData
		    },
		    series : [
		        {
		            name: '状态',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:data.seriesData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	myChart.setOption(option);
}
function initSource(data) {
	var myChart = echarts.init(document.getElementById('count-source'));
	var option = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		        data: ['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis:  {
		        type: 'value'
		    },
		    yAxis: {
		        type: 'category',
		        data: ['周一','周二','周三','周四','周五','周六','周日']
		    },
		    series: [
		        {
		            name: '直接访问',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [320, 302, 301, 334, 390, 330, 320]
		        },
		        {
		            name: '邮件营销',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name: '联盟广告',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [220, 182, 191, 234, 290, 330, 310]
		        },
		        {
		            name: '视频广告',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [150, 212, 201, 154, 190, 330, 410]
		        },
		        {
		            name: '搜索引擎',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [820, 832, 901, 934, 1290, 1330, 1320]
		        }
		    ]
		};
	myChart.setOption(option);
}
/************************************************/
Date.prototype.format = function(format) {
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
    };
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
};
$(function() {
	//应急信息情况统计
	$.post("../statisticsAction/getStateData", function(json) {
		if (json.success) {
			var seriesData = json.data;
			var annualStatisticsCharts = echarts.init(document
					.getElementById('annual_statistics'));
			var app = {};
			option = null;
			app.title = '堆叠柱状图';
			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '1级(特别重大)', '2级(重大)', '3级(较大)', '4级(一般)' ]
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : [ {
					type : 'category',
					data : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月','九月', '十月', '十一月', '十二月' ]
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : seriesData,
			};
			;
			if (option && typeof option === "object") {
				annualStatisticsCharts.setOption(option, true);
			}
		}
	});

	//应急信息播发状态统计
	$.post("../statisticsAction/getStatusData", function(json) {
		if (json.success) {
			var seriesData = json.data;
			var pieStatisticsCharts = echarts.init(document
					.getElementById('pie_statistics'));
			option = {
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					type : 'scroll',
					orient : 'vertical',
					right : 10,
					top : 20,
					bottom : 20,
					data : [ '待提交', '待审核', '未通过审核', '已审核', '待发送', '已发送',
							'发送成功', '等待播发', '正在播发', '播发失败', '播发结束' ],

				//				        selected: data.selected
				},
				series : [ {
					name : '状态',
					type : 'pie',
					radius : '55%',
					center : [ '40%', '50%' ],
					data : seriesData,
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};

			if (option && typeof option === "object") {
				pieStatisticsCharts.setOption(option, true);
			}
		}
	})

	//应急信息类型统计
	$.post("../statisticsAction/getTypeData", function(json) {
		if (json.success) {
			var seriesData = json.data
			var pieStatisticsCharts = echarts.init(document.getElementById('pie_accident_type_statistics'));

			option = {
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					type : 'scroll',
					orient : 'vertical',
					right : 5,
					top : 20,
					bottom : 20,
					data : seriesData.legendData,

				},
				series : [ {
					name : '状态',
					type : 'pie',
					radius : '55%',
					center : [ '40%', '50%' ],
					data : seriesData.seriesData,
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};

			if (option && typeof option === "object") {
				pieStatisticsCharts.setOption(option, true);
			}
		}
	})

})

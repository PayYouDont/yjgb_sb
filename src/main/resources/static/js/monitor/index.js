$(function() {
	initMap();
})
function initMap() {
	var myChart = echarts.init(document.getElementById('map'));
	/*option = {
		    color: ['gold','aqua','lime'],
  		    legend: {
  		        orient: 'vertical',
  		        x:'left',
  		        data:['1级（特别重大）', '2级（重大）', '3级（较大）', '4级（一般）'],
  		        selectedMode: 'single',
  		        selected:{
  		            '上海' : false,
  		            '广州' : false
  		        }
  		    },
  		    toolbox: {
  		        show : true,
  		        orient : 'vertical',
  		        x: 'right',
  		        y: 'center',
  		        feature : {
  		            mark : {show: true},
  		            dataView : {show: true, readOnly: false},
  		            restore : {show: true},
  		            saveAsImage : {show: true}
  		        }
  		    },
  		    dataRange: {
  		        min : 0,
  		        max : 100,
  		        y: '60%',
  		        calculable : true,
  		        color: ['#ff3333', 'orange', 'yellow','lime','aqua']
  		    },
  		    series : [
  		        {
  		            name:'1级（特别重大）',
  		            type:'map',
  		            mapType: 'none',
  		            data:[],
  		            geoCoord: {
  		                // 所有坐标集合
  		            	
  		                '狮峰村':[116.600051,23.77237],
  		                '归湖镇':[116.589361,23.765604],
  		        	    '潮安区应急广播中心':[116.683794,23.469252]
  		            },

  		            markLine : {
  		                smooth:true,
  		                symbol:['none','arrow'],
  		                effect : {
  		                    show: true,
  		                    scaleSize: 1,
  		                    period: 10,
  		                    color: '#fff',
  		                    shadowBlur: 10
  		                },
  		                itemStyle : {
  		                    normal: {
  		                        borderWidth:1,
  		                        lineStyle: {
  		                            type: 'solid',
  		                            shadowBlur: 10
  		                        }
  		                    }
  		                },
  		                data : [
  		                    [{name:'潮安区应急广播中心'}, {name:'狮峰村',value:20}],
  		                    [{name:'潮安区应急广播中心'}, {name:'归湖镇',value:10}]
  		                ]
  		            },
  		            markPoint : {
  		                symbol:'emptyCircle',
  		                symbolSize : function (v){
  		                    return 5 + v/10
  		                },
  		                effect : {
  		                    show: true,
  		                    shadowBlur : 0
  		                },
  		                itemStyle:{
  		                    normal:{
  		                        label:{show:false}
  		                    }
  		                },
  		                data : [
  		                    {name:'归湖镇',value:20},
  		                    {name:'狮峰村',value:10}
  		                ]
  		            }
  		            
  		        },
  		        {
  		            name:'2级（重大）',
  		            type:'map',
  		            mapType: 'none',
  		            data:[],
  		            markLine : {
  		                smooth:true,
  		                effect : {
  		                    show: true,
  		                    scaleSize: 5,
  		                    period: 30,
  		                    color: '#fff',
  		                    shadowBlur: 10
  		                },
  		                itemStyle : {
  		                    normal: {
  		                        borderWidth:1,
  		                        lineStyle: {
  		                            type: 'solid',
  		                            shadowBlur: 10
  		                        }
  		                    }
  		                },
  		                data : []
  		            },
  		            markPoint : {
  		                symbol:'emptyCircle',
  		                symbolSize : function (v){
  		                    return 10 + v/10
  		                },
  		                effect : {
  		                    show: true,
  		                    shadowBlur : 0
  		                },
  		                itemStyle:{
  		                    normal:{
  		                        label:{show:false}
  		                    }
  		                },
  		                data : []
  		            }
  		        },
  		        {
  		            name:'3级（较大）',
  		            type:'map',
  		            mapType: 'none',
  		            data:[],
  		            markLine : {
  		                smooth:true,
  		                effect : {
  		                    show: true,
  		                    scaleSize: 1,
  		                    period: 30,
  		                    color: '#fff',
  		                    shadowBlur: 10
  		                },
  		                itemStyle : {
  		                    normal: {
  		                        borderWidth:1,
  		                        lineStyle: {
  		                            type: 'solid',
  		                            shadowBlur: 10
  		                        }
  		                    }
  		                },
  		                data : []
  		            },
  		            markPoint : {
  		                symbol:'emptyCircle',
  		                symbolSize : function (v){
  		                    return 10 + v/10
  		                },
  		                effect : {
  		                    show: true,
  		                    shadowBlur : 0
  		                },
  		                itemStyle:{
  		                    normal:{
  		                        label:{show:false}
  		                    }
  		                },
  		                data : []
  		            }
  		        },
  		        {
  		            name:'4级（一般）',
  		            type:'map',
  		            mapType: 'none',
  		            data:[],
  		            markLine : {
  		                smooth:true,
  		                symbol: ['none', 'circle'],  
  		                symbolSize : 1,
  		                itemStyle : {
  		                    normal: {
  		                        color:'#fff',
  		                        borderWidth:1,
  		                        borderColor:'rgba(30,144,255,0.5)'
  		                    }
  		                },
  		                data : []
  		            }
  		        }
  		    ]
  		};*/
	/*var option = {
			bmap : {
				center : [116.73519,23.513167 ],  // 天河城
				zoom : 13,
				roam : true,
				mapStyle : {
					styleJson : styleJson
				}
			},
			series : [
				{
					type : 'scatter',
					coordinateSystem : 'bmap',
					data : [ [ 120, 30, 1 ] ]
				},
			]

		}*/
	var option = {
			bmap : {
				center : [116.73519,23.513167 ],  // 天河城
				zoom : 13,
				roam : true,
				mapStyle : {
					styleJson : styleJson
				}
			},
			series : [
				{
					type : 'map',
					data : [ [ 120, 30, 1 ] ]
				},
			]

		}
	myChart.setOption(option);
	var bmap = myChart.getModel().getComponent('bmap').getBMap();
	bmap.addControl(new BMap.MapTypeControl());
}
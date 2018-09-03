(function () {
  require.config({
    paths: {
      echarts: "echarts",
    },
  });

  require(
    [
      "echarts",
      "echarts/chart/main",
      "echarts/chart/map"
    ],
    function (echarts, BMapExtension) {
      $('#map').css({
        height:$('body').height(),
        width: $('body').width()
      });

      // 初始化地图
      var BMapExt = new BMapExtension($('#map')[0], BMap, echarts, {
        enableMapClick: false
      });
      var map = BMapExt.getMap();
      var container = BMapExt.getEchartsContainer();

      // 天河城
      var startPoint = {
        x: 116.73519,
        y: 23.513167
      };

      // 初始化地图,设置中心点坐标和地图级别
      var point = new BMap.Point(startPoint.x, startPoint.y);

      // 创建Map实例
      map.centerAndZoom(point, 13);
      // map.centerAndZoom("南山",);

      // 开启鼠标滚轮缩放
      map.enableScrollWheelZoom(true);

      // 地图自定义样式
      map.setMapStyle({
        styleJson: [
          {
            'featureType': 'land',     //调整陆地颜色
            'elementType': 'geometry',
            'stylers': {
              'color': '#081734'
            }
          },
          {
            'featureType': 'building',   //调整建筑物颜色
            'elementType': 'geometry',
            'stylers': {
              'color': '#04406F'
            }
          },
          {
            'featureType': 'building',   //调整建筑物标签是否可视
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'highway',     //调整高速道路颜色
            'elementType': 'geometry',
            'stylers': {
              'color': '#015B99'
            }
          },
          {
            'featureType': 'highway',    //调整高速名字是否可视
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'arterial',   //调整一些干道颜色
            'elementType': 'geometry',
            'stylers': {
              'color':'#003051'
            }
          },
          {
            'featureType': 'arterial',
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'green',
            'elementType': 'geometry',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'water',
            'elementType': 'geometry',
            'stylers': {
              'color': '#044161'
            }
          },
          {
            'featureType': 'subway',    //调整地铁颜色
            'elementType': 'geometry.stroke',
            'stylers': {
              'color': '#003051'
            }
          },
          {
            'featureType': 'subway',
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'railway',
            'elementType': 'geometry',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'railway',
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'all',     //调整所有的标签的边缘颜色
            'elementType': 'labels.text.stroke',
            'stylers': {
              'color': '#313131'
            }
          },
          {
            'featureType': 'all',     //调整所有标签的填充颜色
            'elementType': 'labels.text.fill',
            'stylers': {
              'color': '#FFFFFF'
            }
          },
          {
            'featureType': 'manmade',
            'elementType': 'geometry',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'manmade',
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'local',
            'elementType': 'geometry',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'local',
            'elementType': 'labels',
            'stylers': {
              'visibility': 'off'
            }
          },
          {
            'featureType': 'subway',
            'elementType': 'geometry',
            'stylers': {
              'lightness': -65
            }
          },
          {
            'featureType': 'railway',
            'elementType': 'all',
            'stylers': {
              'lightness': -40
            }
          },
          {
            'featureType': 'boundary',
            'elementType': 'geometry',
            'stylers': {
              'color': '#8b8787',
              'weight': '1',
              'lightness': -29
            }
          }
        ]
      });
      
      
      $.post("../../statisticsAction/initStatisticsMap",function(data){
          option = {
        		    color: ['gold','aqua','lime'],
          		    title : {
          		        //text: '播发监控',
          		        //subtext:'数据纯属虚构',
          		        
          		        //x:'right'
          		    },
          		    tooltip : {
          		        trigger: 'item',
//          		        formatter: function (v) {
//          		            return v[1].replace(':', ' > ');
//          		        }
          		    },
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
          		                data : [
          		                    /*[{name:'上海'},{name:'包头',value:95}],
          		                    [{name:'上海'},{name:'昆明',value:90}],
          		                    [{name:'上海'},{name:'广州',value:80}],
          		                    [{name:'上海'},{name:'郑州',value:70}],
          		                    [{name:'上海'},{name:'长春',value:60}],
          		                    [{name:'上海'},{name:'重庆',value:50}],
          		                    [{name:'上海'},{name:'长沙',value:40}],
          		                    [{name:'上海'},{name:'北京',value:30}],
          		                    [{name:'上海'},{name:'丹东',value:20}],
          		                    [{name:'上海'},{name:'大连',value:10}]*/
          		                ]
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
          		                data : [
          		                    /*{name:'包头',value:95},
          		                    {name:'昆明',value:90},
          		                    {name:'广州',value:80},
          		                    {name:'郑州',value:70},
          		                    {name:'长春',value:60},
          		                    {name:'重庆',value:50},
          		                    {name:'长沙',value:40},
          		                    {name:'北京',value:30},
          		                    {name:'丹东',value:20},
          		                    {name:'大连',value:10}*/
          		                ]
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
          		                data : [
          		                    /*[{name:'广州'},{name:'福州',value:95}],
          		                    [{name:'广州'},{name:'太原',value:90}],
          		                    [{name:'广州'},{name:'长春',value:80}],
          		                    [{name:'广州'},{name:'重庆',value:70}],
          		                    [{name:'广州'},{name:'西安',value:60}],
          		                    [{name:'广州'},{name:'成都',value:50}],
          		                    [{name:'广州'},{name:'常州',value:40}],
          		                    [{name:'广州'},{name:'北京',value:30}],
          		                    [{name:'广州'},{name:'北海',value:20}],
          		                    [{name:'广州'},{name:'海口',value:10}]*/
          		                ]
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
          		                data : [
//          		                    {name:'福州',value:95},
//          		                    {name:'太原',value:90},
//          		                    {name:'长春',value:80},
//          		                    {name:'重庆',value:70},
//          		                    {name:'西安',value:60},
//          		                    {name:'成都',value:50},
//          		                    {name:'常州',value:40},
//          		                    {name:'北京',value:30},
//          		                    {name:'北海',value:20},
//          		                    {name:'海口',value:10}
          		                ]
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
          		};

          if (myChart && myChart.dispose) {
            myChart.dispose();
          }

          var myChart = BMapExt.initECharts(container);
          window.onresize = myChart.onresize;
          BMapExt.setOption(option, true);
        
      });

    }
  );
    
})();

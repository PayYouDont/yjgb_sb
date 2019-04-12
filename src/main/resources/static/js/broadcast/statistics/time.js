
$(function () {
    selectPeriod();
});
function selectPeriod() {
    $('#fastSelect').combobox({
        onLoadSuccess:function(){
            var period = $("#fastSelect option:selected").val();
            parsePeriod(period)
        },
        onChange : function() {
            var period = $("#fastSelect option:selected").val();
            parsePeriod(period)
        }
    });
    $('#startDate').datebox({
        value:'2018-04-01',
        showSeconds:true,
        editable:false,
        onSelect:function(startDate){
            $('#endDate').datebox().datebox('calendar').calendar({
                validator: function(date){
                    return startDate<=date&&date<=new Date();
                }
            });
        }
    });
    $('#startDate').datebox().datebox('calendar').calendar({
        validator: function(date){
            return date<=new Date();
        }
    });
    $('#endDate').datebox({
        showSeconds:true,
        editable:false,
        onHidePanel:function(){
            var startDate = $('#startDate').datebox('getValue');
            var endDate = $('#endDate').datebox('getValue');
            loadData(startDate,endDate);
        },
        onSelect:function(){
            var startDate = $('#startDate').datebox('getValue');
            var endDate = $('#endDate').datebox('getValue');
            loadData(startDate,endDate);
        }
    });
}
function parsePeriod(period) {
    var startDate,endDate = new Date().format("yyyy-MM-dd");
    switch (period) {
        case 'week':
            startDate = new Date(new Date().getTime()-7*24*60*60*1000).format("yyyy-MM-dd");
            break;
        case 'month':
            startDate = new Date(new Date().getTime()-30*24*60*60*1000).format("yyyy-MM-dd");
            break;
        case 'threeMonth':
            startDate = new Date(new Date().getTime()-90*24*60*60*1000).format("yyyy-MM-dd");
            break;
        case 'year':
            startDate = new Date().getFullYear()-1+"-"+new Date().getMonth()+"-"+new Date().getDay();
            break;
        default :
            startDate = new Date().format("yyyy-MM-dd");
    }
    loadData(startDate,endDate);
}
//根据覆盖区域code加载数据
function loadData(startDate,endDate){
    if(startDate==''||endDate==''){
        return;
    }
    $.ajax({
        url:'../getEmerByDate',
        type:'post',
        data:{startDate:startDate,endDate:endDate},
        dataType:'json',
        success:function (json) {
            if(json.success){
                initBarChart(json.data);
                initPieChart(json.data);
                initLineChart(json.data);
            }
        }
    });
}
function initBarChart(data) {
    var bar = echarts.init(document.getElementById('bar'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        //legend: {data: ['1级(特别重大)', '2级(重大)', '3级(较大)', '4级(一般)']},
        grid: {left: '3%', right: '4%', bottom: '3%', containLabel: true},
        xAxis: [{
            type: 'category',
            data:data.nameData
        }],
        yAxis: [{type: 'value'}],
        series: [{
            data: data.valueData,
            type: 'bar'
        }]
    };
    bar.setOption(option);
}
function initPieChart(data) {
    var pie = echarts.init(document.getElementById('pie'));
    var seriesData = new Array();
    for (var i in data.nameData){
        var name = data.nameData[i];
        var value = data.valueData[i];
        seriesData.push({name:name,value:value});
    }
    var option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data: data.nameData,
        },
        series: [{
            type: 'pie',
            radius: '60%',
            center: ['50%', '50%'],
            data: seriesData,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };
    pie.setOption(option);
}
function initLineChart(data){
    var line = echarts.init(document.getElementById('line'));
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: data.nameData
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: data.valueData,
            type: 'line'
        }]
    };
    line.setOption(option);
}
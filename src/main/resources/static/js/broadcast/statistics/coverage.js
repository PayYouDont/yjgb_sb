//搜索
/*function doSearch(value){
    $('#mainTab').datagrid('load',{
        emergencyName:value
    });
}*/
$(function () {
    selectCodeLevel();
});
function selectCodeLevel() {
    //覆盖区域树
    $("#addressCode").combobox({
        url:'/administrativeAction/getListByCodeLevel?level=3',
        valueField:'code',
        textField:'name',
        onLoadSuccess : function() {
            var data = $(this).combobox('getData');
            $('#addressCode').combobox('setValue',data[0]['code']);
            loadData(data[0]['code'])
        },
        onChange : function() {
            var codeId = $(this).combobox('getValue');
            loadData(codeId);
        }
    });
    //覆盖区域级别树
    $('#codeLevel').combobox({
        onChange : function() {
            var level = $("#codeLevel option:selected").val();
            $('#addressCode').combobox('reload','/administrativeAction/getListByCodeLevel?level='+level);
        }
    });
}
//根据覆盖区域code加载数据
function loadData(code){
    $.ajax({
        url:'../getEmerByAddress',
        type:'post',
        data:{addressCode:code},
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
        seriesData.push({name:name,value:value})
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
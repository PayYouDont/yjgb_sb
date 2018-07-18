var socket;
$(document).ready(function(){
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");
        var host = window.location.host
        socket = new WebSocket("ws://"+host+"/webscoket");
        socket.onopen =function () {
            isconn = true;
        }
        //是否连接
        var isconn;
        //获得消息事件
        socket.onmessage = function(msg) {
            var data = msg.data;
            data = JSON.parse(msg.data)
            if(data.total){
                $('#mainTab').datagrid("loadData",data)
            }else{
                $.messager.alert('系统提示',data.data,'info');
            }
        };
        //关闭事件
        socket.onclose = function() {
            //console.log("Socket已关闭");
            isconn = false;
        };
        //发生了错误事件
        socket.onerror = function() {
            // alert("Socket发生了错误");
            isconn = false;
        }
        $(window).unload(function(){
            socket.close();
            isconn = false;
        });
    }

    $('#mainTab').datagrid({
        /*onBeforeLoad : function(param){
            var page = param.page; //保存下值
            var rows = param.rows;
            delete param.rows; //删掉
            delete param.page; //删掉
            param.pageIndex = page; //这里就是重新命名了
            param.pageSize = rows; //这里就是重新命名了
        },*/
        url:'../nodeAction/showNodeNews',
        // queryParams: {
        //     emergencyName: '',
        // },
        autoRowHeight:false,
        nowrap:true,
        pagination:true,
        pageList:[10,20,30,40,100],
        fitColumns:false,
        checkOnSelect:true,
        singleSelect:false,
        idField:'EBMID',
        columns:[[//ebdId
            {field:"EBMVersion",title:'EBM版本',width:150,align:'center'},
            {field:'EBMID',title:'EBMID',width:150,align:'center'},
            {field:'MsgType',title:'信息类型',width:150,align:'center'},
            {field:'SenderName',title:'发送方名称',width:150,align:'center'},
            {field:'SenderCode',title:'发送方Code',width:150,align:'center'},
            {field:'SendTime',title:'发送时间',width:150,align:'center'},
            {field:'EventType',title:'事件类型',width:150,align:'center'},
            {field:'Severity',title:'严重度',width:150,align:'center'},
            {field:'StartTime',title:'信息开始时间',width:150,align:'center',sortable:"true"},
            {field:'EndTime',title:'信息结束时间',width:150,align:'center',sortable:"true"},
            {field:'LanguageCode',title:'语言Code',width:150,align:'center'},
            {field:'MsgTitle',title:'信息Title',width:150,align:'center'},
            {field:'MsgDesc',title:'信息Desc',width:150,align:'center'},
            {field:'AreaCode',title:'地区Code',width:150,align:'center'},
            {field:'AuxiliaryType',title:'附件类型',width:150,align:'center'},
            {field:'AuxiliaryDesc',title:'附件名称',width:150,align:'center'},
            {field:'Size',title:'附件大小',width:150,align:'center'},
        ]],
        onLoadSuccess:function(data){
        }
    });
});

//搜索
function doSearch(value){
    $('#mainTab').datagrid('load',{
        emergencyName:value
    });
}


//页面刷新
function refreshMyData(){
    $('#mainTab').datagrid("load");
}


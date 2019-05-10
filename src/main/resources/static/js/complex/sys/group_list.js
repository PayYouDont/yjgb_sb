$(document).ready(function(){
	$('#mainTab').datagrid({
        onBeforeLoad : function(param){
            var page = param.page; //保存下值
            var rows = param.rows;
            delete param.rows; //删掉
            delete param.page; //删掉
            param.pageIndex = page; //这里就是重新命名了
            param.pageSize = rows; //这里就是重新命名了
        },
        url:'../groupAction/list',
        fit:true,
        fitColumns:true,
        nowrap:true,
        autoRowHeight:false,
        pagination:true,
        pageList:[10,20,30,40],
        checkOnSelect:true,
        singleSelect:true,
        idField:'id',
		frozenColumns:[[
		            	{field:'ck',width:5,checkbox:true},
		    	        {title:'id',field:'id',width:200,hidden:true},
		    	        {title:'分组名',field:'name',width:200,align:'center'},
		    	        {title:'说明',field:'description',width:200,align:'center',},
					]],
	    columns:[[
	    	{title:'创建时间',field:'createTime',width:200,align:'center'},
	    	{title:'创建人',field:'createBy',width:100,align:'center'},
	    	{title:'修改时间',field:'updateTime',width:200,align:'center'},
	        {title:'修改人',field:'updateBy',width:100,align:'center'}
	    ]]    
	});  
});


//搜索
function doSearch(value){
    $('#mainTab').datagrid('load',{
        search:value
    });
}




//关闭模态框
function closeMyModal(){
    $('#editModal').window('close');
}

//页面刷新
function refreshMyData(){
    $('#mainTab').datagrid("load");
}

//页面刷新(当前页)
function refreshPage(){
    $('#mainTab').datagrid("reload");
}


//增加 （打开模态框）
function add(){
    $('#editIframe').attr('src','../groupAction/toEdit');
    $('#editModal').window('setTitle','角色添加');
    $('#editModal').window('open');
}



//修改
function edit(){
    var checkedData =$('#mainTab').datagrid("getChecked");
    if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
        $.messager.alert('选择提示','请选择一条记录！','info');
        return;
    }
    var v_id=checkedData[0].id;
    $('#editIframe').attr('src','../groupAction/toEdit?id='+v_id);
    $('#editModal').window('setTitle','角色修改');
    $('#editModal').window('open');
}




//删除
function remove(){
    var checkedData =$('#mainTab').datagrid("getChecked");
    if(checkedData.length!=1|| typeof(checkedData)=="undefined"){
        $.messager.alert('选择提示','请选择一条记录！','info');
        return;
    }
    var v_id=checkedData[0].id;
    $.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function(r){
        if (r){
            deleteData(v_id);
        }
    });
}



function deleteData(id){
    $.ajax({
        type: "post",
        data: {id:id},
        //contentType: "application/json",
        url: "../groupAction/delete",
        beforeSend: function () {
            $.messager.progress({
                title : '系统提示',
                text : '请求中，请稍后。。。',
                interval:300
            });;
        },
        success: function (data) {
            $.messager.progress('close');
            if(data=="ok"){
                $.messager.alert('删除提示','删除成功！','info',function(){reloadPage();});
            }else if(data=="used"){
                $.messager.alert('删除提示','该角色正在被多个用户拥有，不能删除','warning');
            }else {
                $.messager.alert('删除提示','删除失败！','error');
            }
        },
//	    complete: function () {//完成响应
//	        $("#submit").removeAttr("disabled");
//	    },
        error: function (data) {
            $.messager.alert('系统提示','异常：'+data,'error')
        }
    });
}








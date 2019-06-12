$(document).ready(function () {
    $('#mainTab').datagrid({
        onBeforeLoad: function (param) {
            var page = param.page; //保存下值
            var rows = param.rows;
            delete param.rows; //删掉
            delete param.page; //删掉
            param.pageIndex = page; //这里就是重新命名了
            param.pageSize = rows; //这里就是重新命名了
        },
        url: '../deviceCertificateAction/list',
        fit: true,
        fitColumns: true,
        nowrap: true,
        autoRowHeight: false,
        pagination: true,
        pageList: [10, 20, 30, 40],
        checkOnSelect: true,
        singleSelect: true,
        idField: 'id',
        frozenColumns: [[
            {field: 'ck', width: 5, checkbox: true},
            {field: 'id', title: 'id', width: 50, hidden: true},
            {field: 'certificateUrl', title: '证书验证平台URL', width: 150, align: 'center', sortable: "true"},
            {field: 'pathSN', title: '平台证书SN', width: 120, align: 'center', sortable: "true"},
            {field: 'smsn', title: '安全设备SN', width: 120, align: 'center'},
        ]],
        columns: [[
            {field: 'status', title: '下发状态', width: 80, align: 'center',formatter:getType},
            {field: 'sendTime', title: '下发时间', width: 80, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 80, align: 'center'},
            {field: 'createBy', title: '创建人', width: 80, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 80, align: 'center'},
            {field: 'updateBy', title: '修改人', width: 80, align: 'center'},
        ]]
    });

});

//搜索
function doSearch(value) {
    $('#mainTab').datagrid('load', {
        devDsn: value
    });
}

function getType(value) {
    let type;
    if(value==null){
        type = '<span style="color: blue;">未下发</span>';
    }else if (value == 0) {
        type = '<span style="color: green;">下发成功</span>';
    } else if (value == 1) {
        type = '<span style="color: red;">下发失败</span>';;
    }
    return type;
}

//关闭模态框
function closeMyModal() {
    $('#editModal').window('close');
}

//页面刷新
function refreshMyData() {
    $('#mainTab').datagrid("load");
}

//页面刷新(当前页)
function refreshPage() {
    $('#mainTab').datagrid("reload");
}

//增加 （打开模态框）
function add() {
    $('#editIframe').attr('src', '../deviceCertificateAction/toEdit');
    $('#editModal').window('setTitle', '设备类型添加');
    $('#editModal').window('open');
}

//（打开模态框）
function edit() {
    var checkedData = $('#mainTab').datagrid("getChecked");
    if (checkedData.length != 1 || typeof (checkedData) == "undefined") {
        $.messager.alert('选择提示', '请选择一条记录！', 'info');
        return;
    }
    var id = checkedData[0].id;
    $('#editIframe').attr('src', '../deviceCertificateAction/toEdit?id=' + id);
    $('#editModal').window('setTitle', '设备类型修改');
    $('#editModal').window('open');
}

//删除
function remove() {
    var checkedData = $('#mainTab').datagrid("getChecked");
    if (checkedData.length != 1 || typeof (checkedData) == "undefined") {
        $.messager.alert('选择提示', '请选择一条信息！', 'info');
        return;
    }
    $.messager.confirm('删除提示', '删除后无法恢复,请谨慎操作！', function (r) {
        if (r) {
            deleteData(checkedData[0].id);
        }
    });
}

function deleteData(id) {
    $.ajax({
        type: "post",
        data: {id: id},
        dataType: "json",
        url: "../deviceCertificateAction/delete",
        success: function (json) {
            var data = json.data;
            if (json.success) {
                $.messager.alert('删除提示', '删除成功！', 'info', function () {
                    refreshPage();
                });
            } else {
                $.messager.alert('删除提示', data, 'warning')
            }
            ;
        },
        error: function (json) {
            $.messager.alert('系统提示', '异常：' + json.data, 'error')
        }
    });
}
function send() {
    var checkedData = $('#mainTab').datagrid("getChecked");
    if (checkedData.length != 1 || typeof (checkedData) == "undefined") {
        $.messager.alert('选择提示', '请选择一条信息！', 'info');
        return;
    }
    $.messager.progress({title: '系统提示', text: '发送中，请稍后。。。', interval: 300});
    var id = checkedData[0].id;
    $.ajax({
        type: "post",
        data: {id: id},
        dataType: "json",
        url: "../deviceCertificateAction/send",
        success: function (json) {
            var data = json.data;
            $.messager.progress('close');
            if (json.success) {
                $.messager.alert('发送提示', '发送成功！', 'info', function () {
                    refreshPage();
                });
            } else {
                $.messager.alert('发送提示', '发送失败:'+json.data, 'error');
                refreshPage();
            }
        },
        error: function (json) {
            $.messager.progress('close');
            $.messager.alert('系统提示', '异常：' + json.data, 'error')
        }
    });
}



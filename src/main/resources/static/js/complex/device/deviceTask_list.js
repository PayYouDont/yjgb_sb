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
        url: '../deviceTaskAction/list',
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
            {field: 'deviceId', title: '设备id', width: 120, align: 'center', sortable: "true"},
            {field: 'status', title: '切换标识', width: 120, align: 'center', sortable: "true"},
        ]],
        columns: [[
            {field: 'taskType', title: '任务类型', width: 80, align: 'center', formatter: getType},
            {field: 'cmdSendId', title: '指令id', width: 80, align: 'center'},
            {field: 'EBMID', title: '应急广播消息编码', width: 80, align: 'center'},
            {field: 'taskStartTime', title: '发生时间', width: 80, align: 'center'},
        ]]
    });

});

//搜索
function doSearch(value) {
    $('#mainTab').datagrid('load', {
        devDsn: value
    });
}

function getType(value, row, index) {
    let type;
    if (value == 1) {
        type = "应急节目源";
    } else if (value == 2) {
        type = "日常节目源";
    } else if (value == 3) {
        type = "电话";
    } else if (value == 4) {
        type = "短信";
    } else if (value == 5) {
        type = "调音台";
    } else if (value == 6) {
        type = "U 盘";
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
    $('#editIframe').attr('src', '../deviceTaskAction/toEdit');
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
    $('#editIframe').attr('src', '../deviceTaskAction/toEdit?id=' + id);
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
        url: "../deviceTaskAction/delete",
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



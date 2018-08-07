function closeParentModal(){
	window.parent.closeMyModal();
}

$(document).ready(function(){
    getMenu();
});

var grid;
function getMenu() {
    var roleId = $("#id").val();
    grid = $('#mainTab').treegrid({
        url:'../menuAction/list?roleId='+roleId,
        autoRowHeight:false,
        fit:true,
        fitColumns:false,
        checkOnSelect:false,
        singleSelect:false,
        idField:'id',
        treeField:'text',
        nowrap:true,
        striped:true,
        animate:true,
        cascadeCheck:true,
        frozenColumns:[[
            {field:'ck',width:5,checkbox:true},
            {title:'id',field:'id',width:20,hidden:true},
            {title:'pid',field:'pid',width:20,align:'center',hidden:true},
            {title:'菜单名称',field:'text',width:150,align:'left',halign:'center'
            }
        ]],
        columns:[[
            {title:'查看',field:'view',width:40,align:'center',
                formatter:function(value,row){
                    if(row.children.length>0){
                        return;
                    }
                    if(row.permissions.view==false){
                        return '<input type="checkbox"/>';
                    }else{
                        return '<input type="checkbox" checked="true"/>' ;
                    }
                }
            },
            {title:'添加',field:'add',width:40,align:'center',
                formatter:function(value,row){
                    if(row.children.length>0){
                        return;
                    }

                    if(row.permissions.add==false){
                        return '<input type="checkbox"/>' ;
                    }else{
                        return '<input type="checkbox" checked="true"/>' ;
                    }
                }
            },
            {title:'修改',field:'moditfy',width:40,align:'left',halign:'center',
                formatter:function(value,row){
                    if(row.children.length>0){
                        return;
                    }
                    if(row.permissions.modify==false){
                        return '<input type="checkbox" />' ;
                    }else{
                        return '<input  type="checkbox" checked="true"/>' ;
                    }
                }
            },
            {title:'删除',field:'delete',width:40,align:'left',halign:'center',
                formatter:function(value,row){
                    if(row.children.length>0){
                        return;
                    }
                    if(row.permissions.delete==false){
                        return '<input type="checkbox"/>' ;
                    }else{
                        return '<input type="checkbox" checked="true" />' ;
                    }
                }
            }
        ]],
        onCheck: function(rows){
            checkedNode(rows);
        },
        onUncheck:function (rows) {
            unCheckNode(rows);
        },
        onCheckAll:function (rows) {
            checkedNode(rows);
        },
        onUncheckAll:function (rows) {
            unCheckNode(rows);
        },
        onClickCell:function (field,row) {
            var permis = row.permissions;
            var checked = permis[field];
            if(checked){
                checked = false;
            }else{
                checked = true;
            }
            permis[field] = checked;
            row.permissions = permis;
        }
    });
}
//var editId = 0;
//选中节点时勾选所有权限
function checkedNode(rows) {
    var t = $('#mainTab');
    var opts = t.treegrid("options");
    if (opts.checkOnSelect && opts.singleSelect) {
        return;
    }
    var idField = opts.idField, id = rows[idField];
    var nodes = t.treegrid("getChildren", id);
    if(nodes.length>0){
        for(var i=0;i<nodes.length;i++){
            var n = nodes[i];
            t.treegrid("checkRow", n[idField]);
        }
    }else{
        var permis = rows.permissions;
        for(i in permis){
            permis[i] = true;
        }
        t.treegrid("refresh",id);
    }
}
//取消节点时取消所有权限
function unCheckNode(rows){
    var t = $('#mainTab');
    var opts = t.treegrid("options");
    if (opts.checkOnSelect && opts.singleSelect) {
        return;
    }
    var idField = opts.idField, id = rows[idField];
    var nodes = t.treegrid("getChildren", id);
    if(nodes.length>0){
        for(var i=0;i<nodes.length;i++){
            var n = nodes[i];
            t.treegrid("uncheckRow", n[idField]);
        }
    }else{
        var permis = rows.permissions;
        for(i in permis){
            permis[i] = false;
        }
        t.treegrid("refresh",id);
    }
}


//保存设备类型 
function mysave(){
	 $.messager.progress({
       title : '系统提示',
       text : '信息提交，请稍后。。。',
       interval:300
   });
    var rows = grid.treegrid("getData");
	$('#dataForm').form('submit',{
		url:'../roleAction/save',
		onSubmit:function(param){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');
            }
            param.menus = JSON.stringify(rows);
			return isValid;	
		},
		success: function(data){
            $.messager.progress('close');
            var json = JSON.parse(data);
			if (json.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.refreshPage();
				});
			}else{
				$.messager.alert('异常', '保存失败!'+json.data,'error');
			}
		}
	});
}
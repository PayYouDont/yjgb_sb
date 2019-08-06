$(document).ready(function(){
	//uitree
	var v_addressCode=$.trim($("#addressCode").val());
	var addresscodeArray=v_addressCode.split(";");
	easyuiInitTree("treeDemo","../administrativeAction/getTreeByCode",addresscodeArray);	
	$('#programDescription').tooltip({
		content: $('<div></div>'),
		//position:'right',
		showEvent: 'click',
		onUpdate: function(content){
			content.panel({
				width: 300,
				height:200,
				border: true,
				title: '输入资源（点击选择）',
				href: ''
			});
		},
		onShow: function(e){
			var t = $(this);
			t.tooltip('tip').unbind().bind('mouseenter', function(){
				t.tooltip('show');
			}).bind('mouseleave', function(){
				t.tooltip('hide');
			});
		}
	});
	$('#mediaId').combobox({
		onSelect:function(option){
			var text = option.text;
			$("#dispalyText").textbox("setValue",text);
		}
	})
})

//1获取输入资源
function queryInnChannel(){
	$.post("/emergencyInfoAction/getPrograme",function(json){
		var data = json.data;
		data = JSON.parse(data);
		if(data==""||typeof(data)=="undefined"||data==null){
			$.messager.progress('close');
			$('#programDescription').tooltip("update",$('<div>获取输入资源失败</div>'));
			$('#programDescription').tooltip("show");
		}else{
			 $.messager.progress('close');	
			var InCh_Prog=data.InChannel.InCh_Prog;
			var html=''
			for (var i = 0; i < InCh_Prog.length; i++) {
				var temp='<div class="programdiv" onclick="selectPro(this);"  id='+InCh_Prog[i].ProgramNum+'>'+InCh_Prog[i].ProgDescript+'</div>'
				html=html+temp;
			}
			$('#programDescription').tooltip("update",$('<div>'+html+'</div>'));
			$('#programDescription').tooltip("show");
		}
	},'json');
}


//选择输入资源
function selectPro(e){
	var v_programId=$(e).attr("id");
	var v_programDescription=$(e).html();
	$("#programId").val(v_programId);
	$("#programDescription").val(v_programDescription);
}



//表单重置
function formClear(){
	$('#dataForm').form('clear');	
}


//返回预案列表
function goback(){
	//返回预案还是正常list页面
	var href = type=="plan"?"toPlan":"toList";
	window.location.href="/emergencyInfoAction/"+href;
}

//保存
function mysave(){
	 $.messager.progress({
         title : '系统提示',
         text : '信息提交，请稍后。。。',
         interval:300
     });
	 
	 var v_addressCode=$("#addressCode").val();
	 if(v_addressCode==""||typeof(v_addressCode)=="undefined"){
		$.messager.progress('close'); 
		$.messager.alert('保存提示', '请选择覆盖区域','warning');
		return;
	 }
	$('#dataForm').form('submit',{
		url:'../emergencyInfoAction/save',
		onSubmit:function(){
			var isValid= $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');	
			}
			return isValid;	
		},
		success: function(data){
			data = JSON.parse(data);
			$.messager.progress('close');	// hide progress bar while submit successfully
			if (data.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					//返回预案还是正常list页面
					var href = type=="plan"?"toPlan":"toList";
					window.location.href="/emergencyInfoAction/"+href;
				});
			}else{
				$.messager.alert('异常', '保存失败!','error');
			}
		}
	});
}
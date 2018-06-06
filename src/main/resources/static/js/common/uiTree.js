//1扩展获取某节点下面的第一级节点
$.extend($.fn.tree.methods,{
    getLeafChildren:function(jq, params){
        var nodes = [];
        $(params).next().children().children("div.tree-node").each(function(){
            nodes.push($(jq[0]).tree('getNode',this));
        });
        return nodes;
    }
});



//2获取节点级别
$.extend($.fn.tree.methods, {
    getLevel:function(jq,target){
        var l = $(target).parentsUntil("ul.tree","ul");
        return l.length+1;
    }
});





//easyui 树
easyuiInitTree=function(elementId,url,initIdArray){
	var element='#'+elementId;
	$(element).tree({    
	    url:url,    
	    checkbox:true,
	    onLoadSuccess:function(node, data){
	    	//展开第一级
	    	var rootnode = $(element).tree('getRoot');//获取根节点
	    	var childrennode1 = $(element).tree('getLeafChildren',rootnode.target);
	    	for (var i = 0; i < childrennode1.length; i++) {
		    	var rootnode = $(element).tree('collapse',childrennode1[i].target);
			}
	    	//初始化勾选
	    	if(initIdArray.length>0){
	    		for (var i = 0; i < initIdArray.length; i++) {
		    		var node = $(element).tree('find',initIdArray[i]);
		    		if(node!=null){
			    		$(element).tree('check', node.target);
		    		}
		    	}	
	    	}
	    },
	    
	    //勾选的时候
	    onCheck:function(node, checked){
	    	var v_addressCode="";
	    	$("#addressCode").val("");
	    	var nodes = $(element).tree('getChecked');
	    	for (var i = 0; i < nodes.length; i++) {
	    		var pNode=$(element).tree("getParent",nodes[i].target);
	    		if(pNode==null){v_addressCode=v_addressCode+nodes[i].id+";"}
	    		else{
	    			if(!pNode.checked){v_addressCode=v_addressCode+nodes[i].id+";"}
	    		}
			}
	    	$("#addressCode").val(v_addressCode);
	    }
	});  
}




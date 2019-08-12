$(function() {
	$("#treeDemo").tree({
		url : "../administrativeAction/getTreeByCode",
		method : "post",
		onSelect : function(node) {
			var text = "", code = "";
			while (node) {
				text = node.text + "/" + text;
				code = node.id + "/" + code;
				node = $("#treeDemo").tree('getParent', node.target);
			}
			text = text.substring(0, text.length - 1);
			code = code.substring(0, code.length - 1);
			window.parent.setAddress(text, code);
		}
	});
})
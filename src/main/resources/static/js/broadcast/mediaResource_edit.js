var uploadType = {//支持上传的文件类型
	"audio/mp3":true,
}
function selectFile(){
	var input = document.createElement("input");
	input.setAttribute("type", "file");
	input.setAttribute("style", "display:none");
	input.setAttribute("accept","audio/mp3")
	input.setAttribute("id", "uploader")
	document.body.appendChild(input);
	input.multiple = true;
	input.click();
	input.addEventListener("change", function() {
		var files = this.files;
		for(var i=0;f=files[i];i++){
			var file = files[i];
			if(!uploadType[file.type]){
				alert("请选择MP3格式");
				return
			}
			initForm(file);
		}
	});
}
function initForm(file){
	$("#fileName").textbox("setValue",file.name);
	$("#fileSize").textbox("setValue",file.size);
	$("#fileType").textbox("setValue",file.type);
}
function removeFile(){
	$("#fileName").textbox("setValue","");
	$("#fileSize").textbox("setValue","");
	$("#fileType").textbox("setValue","");
}
//关闭父级页面模态框
function closeParentModal(){
	window.parent.closeMyModal();
}
function mysave(){
	$.messager.progress({
        title : '系统提示',
        text : '信息提交，请稍后。。。',
        interval:300
    });
	var isValid = $('#dataForm').form('enableValidation').form('validate');
	if (!isValid){
		$.messager.progress('close');
		return;
	}

	var formData = new FormData(document.getElementById("dataForm"));
	var uploader = document.getElementById("uploader");
	var file;
	if(uploader){
		file =uploader.files[0];
		formData.append("file", file);
	}
	var id = $("#souceId").val();
	if(id!=null&&id!=""&&file){
		$.messager.alert('异常', '请选择上传资源!','error');
		return;
	}
    $.ajax({
    	url:'../mediaResouceAction/save',
	    type: "post",
	    data:formData,
	    contentType:false,
        processData:false,
        dataType:"json",
	    success: function (json) {
	    	$.messager.progress('close');
	    	if (json.success){
				$.messager.alert('系统提示', '保存成功!','info',function(){
					window.parent.closeMyModal();
					window.parent.location.reload();
				});
			}else{
				$.messager.progress('close');
				$.messager.alert('异常', '异常保存失败!/n'+json.data,'error');
			}
	    },
	    error: function (json) {
	    	$.messager.progress('close');
	    }
    })
}
function saveOrReview(){
	if(type=="manage"){
		mysave();
	}else{
		review(true);
	}
}
function closeOrReview(){
	if(type=="manage"){
		closeParentModal();
	}else{
		review(false);
	}
}
function review(isPass){
	if(isPass==true){
		$("#status").val(1);
	}else if(isPass==false){
		$("#status").val(2);
	}
	mysave();
}
function playFile(){
	var $player = $("#player");
	if($player.hasClass("play")){
		$player.removeClass("play");
		$player.addClass("pause");
		playAudio();
	}else{
		$player.addClass("play");
		$player.removeClass("pause");
		audio.pause();
	}
}
var audio;
function playAudio(){
	var fileName = $("#fileName").val();
	var playUrl = "../EBM_media/"+fileName;
	// 添加歌曲源
	$("#audio").attr("src",playUrl);
	// 获得音频元素
	audio = document.getElementById("audio");
	/* 显示歌曲总长度 */
	if (audio.paused) {
		audio.play();
	} else {
		audio.pause();
	}
	audio.play();
	audio.addEventListener('timeupdate', updateProgress, false);
}
//更新播放进度条
function updateProgress(ev) {
	if(audio&&audio!=null){
		var curTime = calcTime(Math.floor(audio.currentTime));
		$(".duration").html(curTime);
	}
}
function calcTime(time) {
	var hour;
	var minute;
	var second;
	hour = String(parseInt(time / 3600, 10));
	if (hour.length == 1)
		hour = '0' + hour;
	minute = String(parseInt((time % 3600) / 60, 10));
	if (minute.length == 1)
		minute = '0' + minute;
	second = String(parseInt(time % 60, 10));
	if (second.length == 1)
		second = '0' + second;
	return minute + ":" + second;
}
function  destroy(){
	if (audio&&!audio.paused) {
		audio.pause();
	} 
	audio = null;
}
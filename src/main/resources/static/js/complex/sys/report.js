function reportToSuperior(a) {
    var $input =  $(a).parent().find('input');
    var type = $input.attr('textboxname');
    if (!type||type==null||type==''){
        return;
    }
    $.ajax({
        url:'../platformAction/report',
        type:'post',
        data:{EBDType:type},
        dataType:'json',
        success:function (json) {
            if (json.success){
                var code = json.data;
                var msg = json.msg;
                if (code==1){//成功
                    $input.css({'color':'green'})
                }else{
                    $input.css({'color':'red'})
                }
                $input.val(msg)
            }else{
                $input.css({'color':'red'}).val(json.data);
            }
        }
    })
}
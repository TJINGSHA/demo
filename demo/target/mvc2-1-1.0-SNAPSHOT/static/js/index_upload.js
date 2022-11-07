$(function(){

    var root = $("#root").val();
    //加载书籍分类
    $.ajax({
        async:false,
        type:'get',
        url:root+'/book/cate',
        data:{},
        success:function (result){
            $(result).each(function(){
                var option = $('<option value="'+this.cid+'">'+this.ctitle+'</option>');
                option.appendTo($("#bcid"));
            });
        },
        error:function (){
            alert('系统异常');
        }
    });

    $("#btn_upload").click(function(){
        $("#modal_upload").modal("show");
    });

    $("#picture").change(function(){
        if($(this).length>0) {
            $("#imageshow").attr("src", window.URL.createObjectURL($(this)[0].files[0]));
        }
    });

    function fun_reset(){
        $("button[type='reset']").trigger('click');
        $("#imageshow").attr("src",root+"/static/images/empty.jpg");
    }

    $("#modal_upload").on('hidden.bs.modal',fun_reset);

    $("#btn_submit").click(function(){

        var picture = $("#picture");
        var bcid = $("#bcid");
        var btitle = $("#btitle");
        var bauthor = $("#bauthor");
        var bpublish = $("#bpublish");
        var resource = $("#resource");
        if(picture.val().length==0){
            alert("封面图片不能为空");
            return;
        }
        if(picture[0].files[0].size>(4*1024*1024)){
            alert("图片大小超过上限4MB");
            return;
        }
        if(bcid.val().length==0){
            alert("书籍分类不能为空");
            return;
        }
        if(btitle.val().length==0){
            alert("书籍名称不能为空");
            return;
        }
        if(bauthor.val().length==0){
            alert("作者姓名不能为空");
            return;
        }
        if(bpublish.val().length==0){
            alert("出版日期不能为空");
            return;
        }
        if(resource.val().length==0){
            alert("资源文件不能为空");
            return;
        }
        if(resource[0].files[0].size>(28*1024*1024)){
            alert("资源文件超过上限28MB");
            return;
        }

        var fd = new FormData();
        fd.append("picture",picture[0].files[0]);
        fd.append("resource",resource[0].files[0]);
        fd.append("bcid",bcid.val());
        fd.append("btitle",btitle.val());
        fd.append("bauthor",bauthor.val());
        fd.append("bpublish",bpublish.val()+" 0:00:00");

        $.ajax({
            async:false,
            type:'post',
            url:root+'/book/upload',
            contentType: false,
            processData: false,
            data:fd,
            dataType:'json',
            success:function(result){
                alert(result.message);
                if(200==result.code){
                    fun_reset();
                }
            },
            error:function(){
                alert('系统延时');
            }
        });

    });
});
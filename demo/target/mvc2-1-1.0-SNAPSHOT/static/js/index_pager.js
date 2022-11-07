$(function(){
    var index = 1;
    var last = 0;
    var key = "";
    var root = $("#root").val();
    fun_pager();
    $("#a_first").click(function(){
        if(index==1) return;
        index = 1;
        fun_pager();
    });
    $("#a_prev").click(function(){
        if(index==1) return;
        index--;
        fun_pager();
    });
    $("#a_next").click(function(){
        if(index==last)return;
        index++;
        fun_pager();
    });
    $("#a_last").click(function(){
        if(index==last)return;
        index = last;
        fun_pager();
    });
    $("#btn_search").click(function(){
        key = $("#key").val();
        //每次搜索 从第一页开始
        index = 1;
        fun_pager();
    });
    $("#btn_refresh").click(function(){
        index = 1;
        fun_pager();
    });
    function fun_pager() {
        $.ajax({
            type: 'POST',
            url: root + "/book/pager",
            dataType: 'json',
            data: {'index': index, 'key': key},
            success: function (result) {
                $("#tbl_book>tbody").empty();
                if (result.pageTotal == 0) {
                    $("#no_data").show();
                    $("#tbl_book").hide();
                } else {
                    $("#no_data").hide();
                    last = result.pageCount;
                    $(result.pageItems).each(function () {
                        var row = $("<tr></tr>");
                        row.append('<td><img style="width: 80px;" class="img-thumbnail" ' +
                            'src="' + root + '/upload/pictures/' + this.bpicture + '" /></td>');
                        row.append('<td>' + this.btitle + '</td>');
                        row.append('<td>' + this.cateVO.ctitle + '</td>');
                        row.append('<td>' + this.bauthor + '</td>');
                        row.append('<td>' + this.bpublish + '</td>');
                        row.append('<td><a href="'+root+'/book/download?id='+this.bid+'">下载</a></td>');
                        row.appendTo($("#tbl_book>tbody"));
                    });
                    $("#ul_pager").empty();
                    for(var i=result.pageBegin;i<=result.pageEnd;i++){
                        var li = $('<li><a lang='+i+' href="javascript:void(0);">'+i+'</a></li>');
                        li.find('a').click(function (){
                            index = $(this).attr("lang");
                            fun_pager();
                        });
                        li.appendTo($("#ul_pager"));
                    }
                    $("#tbl_book").show();
                }
            },
            error: function () {
                alert('网络延时');
            }
        });
    }
});
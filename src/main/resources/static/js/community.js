
//评论问题
function commentFromProblem() {
    var id = $("#problemId").val();
    var content = $("#problemContent").val();
    comment(id,content,1);
}
//评论通用
function comment(id,content,type) {
    if(!content){
        alert("评论内容不能为空");
    }
    var data = JSON.stringify({
        "parentId":id,
        "content":content,
        "type":type
    });
    $.ajax({
        type:"POST",//发送的方式
        contentType:"application/json",
        url:"/comment",//发送的路径
        dataType:"json",//服务器返回的数据类型
        data:data,//发送的数据
        success:function (result) {
            if(result.code == 200){
                //$("#commentSession").hide();
                location.reload();
            }else{
                //用户未登录
                if(result.code == 2001){
                    if(confirm(result.message)){
                        window.open("https://github.com/login/oauth/authorize?client_id=689c94ad0ea8ba8267bb&redirect_uri=http://huan.cross.echosite.cn/callback&scope=user&state=1");
                        localStorage.setItem("closeable",true);
                    }
                }
                alert(result.message);
            }
        }
    });
}

function commentFromComment(e) {
    var id = $(e).attr("data-id");
    var content = $("#content-" + id).val();
    comment(id,content,2);
    //console.log(id);
}

function collapseComments(e) {
    var id = $(e).attr("data-id");
    if($("#comment-" + id).hasClass("in")){
        $("#comment-" + id).removeClass("in");
        $(e).removeClass("active");
    }else{
        //debugger
        $("#comment-" + id).addClass("in");
        $(e).addClass("active");
        rander(id);
    }
    //console.log(id);
}

function rander(id) {
    $.ajax({
        type:"GET",//发送的方式
        url:"/comment/" + id,//发送的路径
        dataType:"json",//服务器返回的数据类型
        success:function (result) {
            if(result.code == 200){
                var data = result.data;
                var content = "";
                if(data == null || data.length <= 0){
                    $("#sub_comments-" + id).remove();
                }

                for(i = 0;i < data.length;++i){
                    var date = new Date().getTime();
                    var stemp = (date - data[i].gmtCreate) / 1000;
                    var time = "";
                    if(stemp < 60){
                        time += parseInt(stemp) + "秒前";
                    }else if(stemp < 3600){
                        time += parseInt(stemp / 60) + "分钟前";
                    }else if(stemp < 3600 * 24){
                        time += parseInt(stemp / 3600) + "小时前";
                    }else{
                        time += parseInt(stemp / (3600 * 24)) + "天前";
                    }
                //    console.log(parseInt(stemp));
                    content += "<div class=\"media\">\n" +
                        "            <div class=\"media-left\">\n" +
                        "                   <a href=\"#\">\n" +
                        "                         <img class=\"media-object img-rounded img-small\"\n" +
                        "                              src=\""+ data[i].account.avatarUrl +"\">\n" +
                        "                   </a>\n" +
                        "            </div>\n" +
                        "            <div class=\"media-body\">\n" +
                        "                   <h4 class=\"media-heading\">\n" +
                        "                       <span>"+ data[i].account.userName +"</span>\n" +
                        "                   </h4>\n" +
                        "                   <span>"+ data[i].content +"</span>\n" +
                        "                   <span class='pull-right text-desc'>"+ time +"</span>\n" +
                        "            </div>\n" +
                        "       </div>\n" +
                        "       <hr class='sub-comment-line'>";
                }
                $("#sub_comments-" + id).html(content);
                console.log(data);
            }else{
                //用户未登录
                if(result.code == 2001){
                    if(confirm(result.message)){
                        window.open("https://github.com/login/oauth/authorize?client_id=689c94ad0ea8ba8267bb&redirect_uri=http://huan.cross.echosite.cn/callback&scope=user&state=1");
                        localStorage.setItem("closeable",true);
                    }
                }
                alert(result.message);
            }
        }
    });
}

function selectTag(value) {

    var pre = $("#tags").val();
    var items = pre.split(",");

    var flag = false;
    for(var i = 0;i < items.length;++i){
        if(items[i] == value){
            flag = true;
            break;
        }
    }
    if(flag == false){
        if (pre) {
            $("#tags").val(pre + ',' + value);
        } else {
            $("#tags").val(value);
        }
    }

}

function showSelectTag() {
    $("#navTag").show();
}

function comment() {
    var problemId = $("#problemId").val();
    var problemContent = $("#problemContent").val();
    if(!problemContent){
        alert("评论内容不能为空");
    }
    var data = JSON.stringify({
        "parentId":problemId,
        "content":problemContent,
        "type":1
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

// 上传文件
function clickUpload(){
    $("#selectFile").text("已选择文件");
    $("#selectFile").attr("disabled",true);
    $("#msg1").text("");
    var checkFile = $("#uploadFile").val();
    var msgInfo = "";
    if(null==checkFile || ""==checkFile){
        $("#msg1").text("文件为空,请选择文件!");
    }else{
        var formData = new FormData(document.getElementById("FileuploadForm"));
        $.ajax({
            type: "post",
            enctype:'multipart/form-data',
            url: '/upload',
            data: formData,
            cache:false,
            processData:false,
            contentType:false,
            error:function(result){
                console.log("error");
                console.log(result);
                flag = false;
                $("#msg1").text("访问服务器错误，请重试！");
            },
            success: function(result){
                console.log("success");
            },
            xhr: function () {
                var xhr = $.ajaxSettings.xhr();
                if (xhr.upload) {
                    //处理进度条的事件
                    xhr.upload.addEventListener("progress", progressHandle, false);
                    //加载完成的事件
                    xhr.addEventListener("load", completeHandle, false);
                    //加载出错的事件
                    xhr.addEventListener("error", failedHandle, false);
                    //加载取消的事件
                    xhr.addEventListener("abort", canceledHandle, false);
                    //开始显示进度条
                    showProgress();
                    return xhr;
                }
            }
        }, 'json');
    }
}
var start = 0;
//显示进度条的函数
function showProgress() {
    start = new Date().getTime();
    $(".progress-body").css("display", "block");
}
//隐藏进度条的函数
function hideProgress() {
    $("#uploadFile").val('');
    $('.progress-body .progress-speed').html("0 M/S, 0/0M");
    $('.progress-body percentage').html("0%");
    $('.progress-body .progress-info').html("请选择文件并点击上传文件按钮");
    // $(".progress-body").css("display", "none");
}
//进度条更新
function progressHandle(e) {
    $('.progress-body progress').attr({value: e.loaded, max: e.total});
    var percent = e.loaded / e.total * 100;
    var time = ((new Date().getTime() - start) / 1000).toFixed(3);
    if(time == 0){
        time = 1;
    }
    $('.progress-body .progress-speed').html(((e.loaded / 1024) / 1024 / time).toFixed(2) + "M/S, " + ((e.loaded / 1024) / 1024).toFixed(2) + "/" + ((e.total / 1024) / 1024).toFixed(2) + " MB. ");
    $('.progress-body percentage').html(percent.toFixed(2) + "%");
    if (percent == 100) {
        $('.progress-body .progress-info').html("上传完成,后台正在处理...");
    } else {
        $('.progress-body .progress-info').html("文件上传中...");
    }
};
//上传完成处理函数
function completeHandle(e) {
    var file = $("#uploadFile")[0].files;
    sessionStorage.setItem("fileName",file.name);
    $('.progress-body .progress-info').html("上传文件完成。");
    var title = document.getElementById("title2").value;
    if (title==null||title==""){
        alert("请设置标题!否则无法投稿!")
    }
    var t1 = setInterval(pdTitle,1000);//循环验证是否有标题
    //setTimeout(hideProgress, 2000);
};
//上传出错处理函数
function failedHandle(e) {
    $('.progress-body .progress-info').html("上传文件出错, 服务不可用或文件过大。");
};
//上传取消处理函数
function canceledHandle(e) {
    $('.progress-body .progress-info').html("上传文件取消。");
};
function pdTitle(){
    var title = document.getElementById("title2").value;
    if (title==null||title==""){
        console.log("标题不能为空")
        document.getElementById("msg1").style.color="red";
        $("#msg1").text("标题不能为空！");
    }else{
        $("#msg1").text("");
        sessionStorage.setItem("videoTitle",title);
        document.getElementById("upload").style.pointerEvents="all";//标题有了就可以投稿
    }

}
function tougaoMessage(){
    var title = document.getElementById("title2").value;
    var tag = document.getElementById("selectTag").value;
    console.log(title+tag);
    $.ajax({
        type: "POST",
        dataType: "text",
        url: "/tougao",
        data: {
            "videoTitle": title,
            "tag": tag
        },
        success: function (result) {
            console.log("success");
            window.location.href='file';
            alert("投稿成功,等待审核")
        },
        error: function(result) {
            console.log("error");
            console.log(result);

        },
    });
}


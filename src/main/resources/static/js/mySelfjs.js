function mysign(){
    var newsign = document.getElementById("selfSign").value;
    console.log("新签名:"+newsign);
    $.ajax({
        type: "POST",
        dataType: "text",
        url: "/refreshSign",
        data: {
            "newsign": newsign,
        },
        success: function (result) {
            console.log("success");
        },
        error: function(result) {
            console.log("error");
            console.log(result);
        },
    });
}
function shenheTheme(){
    clearAll();
    var divnew = document.getElementById("col-1-shenhe");
    var shenhe = document.getElementById("shenhe");
    shenhe.style.backgroundColor='#3b498d';
    shenhe.style.borderRadius='0 0 10px 10px';
    divnew.style.display = '';

}
function shoucang(){
    clearAll();
    var divnew = document.getElementById("col-1-shoucang");
    var shoucang = document.getElementById("shoucang");
    shoucang.style.backgroundColor='#3b498d';
    shoucang.style.borderRadius='0 0 10px 10px';
    divnew.style.display = '';
}
function clearAll(){
    if (sessionStorage.getItem("userAccount")!=="admin"){
        clearAllPassShenhe();
        return
    }
    var shenhediv = document.getElementById("col-1-shenhe");
    var zhuyediv = document.getElementById("col-1");
    var shoucangdiv = document.getElementById("col-1-shoucang");
    zhuyediv.style.display = 'none';
    shenhediv.style.display = 'none';
    shoucangdiv.style.display = 'none';

    var shenhe = document.getElementById("shenhe");
    var zhuye = document.getElementById("zhuye");
    var shoucang = document.getElementById("shoucang");
    zhuye.style.backgroundColor='#120826';
    zhuye.style.borderRadius='0 0 0 0';
    shoucang.style.backgroundColor='#120826';
    shoucang.style.borderRadius='0 0 0 0';
    shenhe.style.backgroundColor='#120826';
    shenhe.style.borderRadius='0 0 0 0';
}
function clearAllPassShenhe(){
    var zhuyediv = document.getElementById("col-1");
    var shoucangdiv = document.getElementById("col-1-shoucang");
    zhuyediv.style.display = 'none';
    shoucangdiv.style.display = 'none';

    var zhuye = document.getElementById("zhuye");
    var shoucang = document.getElementById("shoucang");
    zhuye.style.backgroundColor='#120826';
    zhuye.style.borderRadius='0 0 0 0';
    shoucang.style.backgroundColor='#120826';
    shoucang.style.borderRadius='0 0 0 0';

}
function clearAllOtherPeople(){
    var zhuyediv = document.getElementById("col-1");
    var shoucangdiv = document.getElementById("col-1-shoucang");
    zhuyediv.style.display = 'none';
    shoucangdiv.style.display = 'none';
    var zhuye = document.getElementById("zhuye");
    var shoucang = document.getElementById("shoucang");
    zhuye.style.backgroundColor='#120826';
    zhuye.style.borderRadius='0 0 0 0';
    shoucang.style.backgroundColor='#120826';
    shoucang.style.borderRadius='0 0 0 0';
}

function passAudit(){
    var id = document.getElementById("videoId").value;
    if (confirm("确认通过审核吗?")) {
        $.ajax({
            type: "POST",
            dataType: "text",
            url: "/passAudit",
            data: {
                "targetVideoId": id,
            },
            success: function (result) {
                console.log("success");
                location.reload();//刷新页面
                confirm("审核完成!")
            },
            error: function(result) {
                console.log("error");
                console.log(result);
                alert("审核失败!")
            },
        });
    } else {
        console.log("取消审核")
    }
}
function openFolder(id){
    console.log("folderid="+id);
    $.ajax({
        type: "post",
        url: "/getCollect",
        data: {
            "folderId": id,
        },
        success: function (result) {
            console.log(result);
            $('#folder').html(result);

        },
        error: function(result) {
            console.log("error");
            console.log(result);
            alert("刷新失败!")
        },
    });
}

function exitFolder(){

    $.ajax({
        type: "post",
        url: "/exitFolder",
        data: {
        },
        success: function (result) {
            console.log(result);
            $('#folder').html(result);

        },
        error: function(result) {
            console.log("error");
            console.log(result);
            alert("刷新失败!")
        },
    });
}
function exitOtherFolder(otherAccount){

    $.ajax({
        type: "post",
        url: "/exitOtherFolder",
        data: {
            "otherAccount":otherAccount
        },
        success: function (result) {
            console.log(result);
            $('#folder').html(result);

        },
        error: function(result) {
            console.log("error");
            console.log(result);
            alert("刷新失败!")
        },
    });
}
function openOtherFolder(id,otherAccount){
    console.log("folderid="+id);
    $.ajax({
        type: "post",
        url: "/getOtherCollect",
        data: {
            "folderId": id,
            "otherAccount":otherAccount,
        },
        success: function (result) {
            console.log(result);
            $('#folder').html(result);

        },
        error: function(result) {
            console.log("error");
            console.log(result);
            alert("刷新失败!")
        },
    });
}

function newFolder(){
    var coverall = document.getElementById("coverall");
    var newFolderDiv = document.getElementById("newFolderDiv");
    coverall.style.display='';
    newFolderDiv.style.display='';
}
function removeError(){
    var submitError = document.getElementById("submitError");
    submitError.style.display='none';
    submitError.style.background="#323232";
    submitError.value="请输入收藏夹名"
}
function removeCover(){
    var coverall = document.getElementById("coverall");
    coverall.style.display='none';
}
function closeNewFolder(){
    var coverall = document.getElementById("coverall");
    coverall.style.display='none';
    var submitA = document.getElementById("submitA");
            submitA.style.pointerEvents='';
}
function submitNewFolder(id){
    var newFolderName=document.getElementById("newFolderName").value;
    var submitError = document.getElementById("submitError");
    if (newFolderName===""){
        submitError.style.display='';

        setTimeout("removeError()",1500);//1.5秒
        return false
    }
    $.ajax({
        type: "post",
        url: "/newFolder",
        data: {
            "account": id,
            "folderName":newFolderName,
        },
        success: function (result) {
            console.log("新建成功");
            submitError.style.background="#87c2f4";
            submitError.textContent="新建成功"
            submitError.style.display='';
            var submitA = document.getElementById("submitA");
            submitA.style.pointerEvents="none";
            setTimeout("removeError()",1500);//1.5秒
            setTimeout("removeCover()",1500);
            $('#folder').html(result);

        },
        error: function(result) {
            console.log("error");
            console.log(result);
            alert("新建失败!")
        },
    });
}
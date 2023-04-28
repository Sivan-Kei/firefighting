$(".get-register").click(function () {
$(".manager-box").removeClass("right").addClass("left").removeClass("active");
});

$(".get-login").click(function () {
    $(".manager-box").removeClass("left").addClass("right").addClass("active");
});

$(".btn-register,.btn-login").click(function () {
    $(".loading").fadeIn("slow");
});

function logout() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
    //返回登录页面或者主页
    window.location.href = "/CLogin.html";
}

function NumLimit() {
    var topPrice = $("#topPrice").val();
    var botPrice = $("#botPrice").val();
    if (parseInt(botPrice) > parseInt(topPrice)) {
        alert("最低价不能高于最高价！");
        $("#topPrice").val(parseInt(botPrice)+100);
    }
};
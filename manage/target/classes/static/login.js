//登录ajax，登录成功后获取后台返回的token，并把token保存到cookie中
function login() {
    let phonenumber = $("input[name='phonenumber']").val();
    let password = $("input[name='password']").val();
    alert(phonenumber);
    $.ajax({
        url: "/login/user",
        type: "POST",
        dataType: "json",
        data: {phonenumber: phonenumber, password: password},
        success: function (result) {
            //保存token用来判断用户是否登录，和身份是否属实
            $.cookie('token', result.token);
        }
    })
}

//请求数据的ajax，需要从cookie读取token放入head传给后台。
function loadDeptTree() {
    $.ajax({
        // 自定义的headers字段，会出现option请求，在GET请求之前，后台要记得做检验。
        headers: {
            token: $.cookie('token')
        },
        url: urlHead + "/sys/dept/tree",
        type: 'GET',
        dataType: 'json',
        success : function (result) {
        }
    })
}

// 注销，清空所有cookie(或者只清空保存着token的Cookie就行)
function logout() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(var i = keys.length; i--;)
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
    }
    //返回登录页面或者主页
    window.location.href = "login.html";
}

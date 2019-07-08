
function hists() {
        var url="/stock/single.do";
        var code=getUrlParam("code");
        var data = {code: code};
        $.getJSON(url, data, function (result) {
            if (result.state == 200) {
                var list = result.data;
                showStock(list);
            } else {
                alert(result.message);
            }
        });
    }
    
    function recently() {
        var url="/stock/change.do";
        var code=getUrlParam("code");
        var data = {code: code};
        $.getJSON(url, data, function (result) {
            if (result.state == 200) {
                var list = result.data;
                showrecen(list);
            } else {
                alert(result.message);
            }
        });
    }

    function showStock(list) {
        var result = "日期，价格";
        for (var i = 0; i < list.length; i++) {
            result += list[i].datime + "，" + list[i].end+ "<br>";
        }
        $("pre").html(result);
    }
        function showrecen(list) {
            var result="日期，最高价格，最低价格";
            for (var i = 0; i < list.length; i++) {
                result+=list[i].datime+ "，"+list[i].sprice+"，"+list[i].eprice+"<br>";
            }
            $("pre").html(result);
            }
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return parseInt(unescape(r[2])); return null; //返回参数值
}



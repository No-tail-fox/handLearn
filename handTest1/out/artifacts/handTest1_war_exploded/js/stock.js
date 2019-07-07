$(function () {

    //on()方法绑定事件可以区别事件源
    //click()方法绑定事件，无法区别事件源
    var code=$.query.get("code");
    $("form[name=\'form1\']").data("code",code);
    //当加载这个网页时，注册按钮
    $("input[name=\'history\']") .on('click', '.online', hist);
    $("input[name=\'recently\']") .on('click', '.online', recently);
});

        function hist() {
        var url="/stock/single.do";
        var code=$.query.get("code");
        var data = {code: code};
        $.getJSON(url, data, function (result) {
            if (result.state == SUCCESS) {
                var list = result.data;
                showStock(list);
            } else {
                alert(result.message);
            }
        });
    }
    
    function recently() {
        var url="/stock/change.do";
        var code=$.query.get("code");
        var data = {code: code};
        $.getJSON(url, data, function (result) {
            if (result.state == SUCCESS) {
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
            result += list[i].get("datime") + "，" + list[i].get("end") + "<br>";
        }
        $("pre").html(result);
    }
        function showrecen(list) {
            var result="日期，最高价格，最低价格";
            for (var i = 0; i < list.length; i++) {
                result+=list[i].get("datime")+ "，"+list[i].get("sprice")+"，"+list[i].get("eprice")+"<br>";
            }
            $("pre").html(result);
            }




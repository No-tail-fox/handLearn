$(function () {

    //on()方法绑定事件可以区别事件源
    //click()方法绑定事件，无法区别事件源
    //当加载这个网页时，注册上下页
    $("input[name=\'pre\']") .on('click', '.online', prepage);
    $("input[name=\'next\']") .on('click', '.online', nextpage);
    load(getUrlParam("page"));
});

        function load(page) {
        var url="/stock/list.do";
        var data = {page:page};
        $.getJSON(url, data, function (result) {
            if (result.state ==200) {
                var list = result.data;
                showStock(list);
           } else {
                alert(result.message);
           }
        });
    }
    
    function showStock(list) {
        var result="";
        for (var i=0;i<list.length;i++) {
            result+="<a href=\"stock.html?code="+list[i].code+"\">"+list[i].name+"</a>,"+ list[i].code+","+ list[i].yesterday+","+list[i].today+","+list[i].now+"<br>";
        }
        $("pre").html(result);
    }

    function prepage()
    {
        var npage=parseInt(getUrlParam("page"))-1;
        if(npage<1) {
            npage = 1;
        }
        console.log("当前页面值"+npage);
        window.location.href="http://localhost:8082/html/stocklist.html?page="+npage;
    }
    function nextpage()
    {
    var npage=parseInt(getUrlParam("page"));
    npage++;
    alert(npage);
    window.location.href="http://localhost:8082/html/stocklist.html?page="+npage;
    }
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
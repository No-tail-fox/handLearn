$(function () {

    //on()方法绑定事件可以区别事件源
    //click()方法绑定事件，无法区别事件源
    $("form[name=\'form1\']").data("page",1);
    //当加载这个网页时，注册上下页
    $("input[name=\'pre\']") .on('click', '.online', prepage);
    $("input[name=\'next\']") .on('click', '.online', nextpage);
    load(1);
});

        function load(page) {
        var url="/stock/list.do";
        var data = {page: page};
        $.getJSON(url, data, function (result) {
            if (result.state == SUCCESS) {
                var list = result.data;
                showStock(list);
            } else {
                alert(result.message);
            }
        });
    }
    
    function showStock(list) {
        var result="";
        for (var i = 0; i < list.length; i++) {
            result+="<a href=\"stock.html?code="+list[i].get("code")+"\">"+list[i].get("name")+"</a>,"+ list[i].get("code")+","+ list[i].get("yesterday")+","+list[i].get("today")+","+list[i].get("now")+"<br>";
        }
        $("pre").html(result);
    }

    function prepage()
    {
        var page=$("form[name=\'form1\']").data("page")-1;
        if(page<1) {
            page = 1;
        }
        load(page);
    }
function nextpage()
{
    var page=$("form[name=\'form1\']").data("page")+1;
    load(page);
}


package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StockService;
import util.JsonResult;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stock")
public class Controle {
    @Resource
    StockService stockservice;

    @ResponseBody
    @RequestMapping("/list.do")
    public JsonResult listStock(int page) {
        ArrayList<Map<String,Object>> list=null;
        try {
            list=stockservice.listStock(page*40);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonResult(list);
    }

    @ResponseBody
    @RequestMapping("/change.do")
    public Object listOverFive(String code){
        List<Map<String,Object>> list=null;
        list=stockservice.salayUptoFive(code);
        return new JsonResult(list);
    }

    @ResponseBody
    @RequestMapping("/single.do")
    public Object singleHistory(String code){
        List<Map<String,Object>> list=null;
        list=stockservice.singleStock(code);
        return new JsonResult(list);
    }

}

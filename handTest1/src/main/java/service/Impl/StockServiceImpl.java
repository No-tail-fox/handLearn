package service.Impl;

import dao.StockDao;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StockService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller("stockservice")
public class StockServiceImpl implements StockService {
    @Resource
    StockDao stockdao;
    @ResponseBody
    public List<Map<String, Object>> singleStock(String stockCode) {
        ArrayList<Map<String,Object>> list=null;
        if(stockCode.charAt(1)=='h'){
            list=(ArrayList)stockdao.SHsingleStock(stockCode);
        }
        else if (stockCode.charAt(1)=='z'){
            list=(ArrayList)stockdao.SHsingleStock(stockCode);
        }
        System.out.println(list);
        return list;
    }
    @ResponseBody
    public List<Map<String, Object>> salayUptoFive(String stockCode) {
        ArrayList<Map<String,Object>> list=null;
        if(stockCode.charAt(1)=='h'){
            list=(ArrayList)stockdao.SHsalayUptoFive(stockCode);
        }
        else if (stockCode.charAt(1)=='z'){
            list=(ArrayList)stockdao.SZsalayUptoFive(stockCode);
        }
        return list;
    }
    @ResponseBody
    public ArrayList<Map<String,Object>> listStock(int id) throws IOException {
        ArrayList<String> list=stockdao.listStock(id);
        ArrayList<Map<String,Object>> result=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String, Object>();
        String temp="";
        String uri="http://hq.sinajs.cn/list=";
        for(String code :list)
        {
            URL url=new URL(uri+code);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection() ;
            conn.setDoInput(true);
            if (conn.getResponseCode() == 200) {
                //用getInputStream()方法获得服务器返回的输入流
                InputStream in = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                temp = br.readLine();
                temp = temp.substring(21, temp.length() - 2);
                br.close();
            }
            String team[]=temp.split("\\,");
            map.put("name",team[0]);
            map.put("code",code);
            map.put("yesterday",team[2]);
            map.put("today",team[1]);
            map.put("now",team[3]);
            result.add(map);
            map=new HashMap<String, Object>();
        }
        return result;
    }
}

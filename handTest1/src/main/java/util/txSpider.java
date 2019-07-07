package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public  class txSpider {
    public static ArrayList<String> stockCode =new ArrayList<String>();

    public static void spider(String uri,int year) throws Exception {
        ArrayList<String> stock;
        FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\history.txt"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fo));
        int xx=0;
        for(String code:stockCode)
        {
            xx++;
            stock=new ArrayList<String>();
            for(int i=year;i<=19;i++)
            {
                if(i==9)
                    uri="http://data.gtimg.cn/flashdata/hushen/daily/09/"+code+".js?maxage=43201";
                else
                    uri="http://data.gtimg.cn/flashdata/hushen/daily/"+i+"/"+code+".js?maxage=43201";
                URL url=new URL(uri);
                HttpURLConnection  conn=(HttpURLConnection)url.openConnection() ;
                conn.setDoInput(true);
                String temp;
                if (conn.getResponseCode() == 200) {
                    //用getInputStream()方法获得服务器返回的输入流
                    InputStream in = conn.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(in));
                    br.readLine();
                    while((temp=br.readLine())!=null)
                    {
                        if(temp.length()<10)
                            break;
                        String stocks[]=temp.split(" ");
                        stock.add(code+","+stocks[0]+","+stocks[1]+","+stocks[2]);
                    }
                    br.close();
                }
            }
            for(String value:stock)
            {
                bw.write(value);
                bw.newLine();
            }
            bw.flush();
            System.out.println("第"+xx+"个代码");
        }
        bw.close();
    }

    /**
     * 爬取所有的股票代码
     */
    public static void dealCode1()
    {
        //+"bsz/chr&p=1&o=0&l=100000&v=list_data"
    }

    public static void getStockCode(String uri) throws Exception {
        uri="http://stock.gtimg.cn/data/index.php?appn=rank&t=rank";
        List<String> urilist=new ArrayList<String>();
        urilist.add(uri+"bsz/chr&p=1&o=0&l=100000&v=list_data");
        urilist.add(uri+"asz/chr&p=1&o=0&l=100000&v=list_data");
        urilist.add(uri+"bsh/chr&p=1&o=0&l=100000&v=list_data");
        urilist.add(uri+"ash/chr&p=1&o=0&l=100000&v=list_data");
        for (int i=0;i<4;i++) {
            URL url = new URL(urilist.get(i));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            String str="";
            String temp;
            if (conn.getResponseCode() == 200) {
                //用getInputStream()方法获得服务器返回的输入流
                InputStream in = conn.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                while ( (temp=br.readLine())!=null) {
                    str += temp;
                }
               // System.out.println(str);
                jsonToList(str);
                br.close();
                //os.close();
            }
        }
        System.out.println(stockCode.size());
       FileOutputStream fo=new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\data.txt"));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fo));
        for(String value:stockCode)
        {
            bw.write(value);
            bw.newLine();
        }
        bw.close();
    }

    public static void  jsonToList(String jsonData)
    {
        int index=jsonData.length();
        jsonData=jsonData.substring(62,index-3);
        String [] temp = jsonData.split(",");
        for(String str:temp)
        {
            stockCode.add(str);
        }
    }
}

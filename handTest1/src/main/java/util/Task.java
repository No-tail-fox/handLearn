package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class Task {

    @Scheduled(cron = "0 0 12 * * ?")
    public void testTask(){
        try {
            txSpider.getStockCode("http://stock.gtimg.cn/data/index.php?appn=rank&t=rank");
            txSpider.spider("http://data.gtimg.cn/flashdata/hushen/daily/09/",18);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
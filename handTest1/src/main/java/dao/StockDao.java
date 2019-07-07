package dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("stockdao")
public interface StockDao {
    List<Map<String,Object>> SZsingleStock(String stockCode);
    List<Map<String,Object>> SHsingleStock(String stockCode);
    List<Map<String,Object>> SHsalayUptoFive(String stockCode);
    List<Map<String,Object>> SZsalayUptoFive(String stockCode);
    ArrayList<String> listStock(int id);
}

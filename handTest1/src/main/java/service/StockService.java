package service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StockService {
    List<Map<String,Object>> singleStock(String stockCode);
    List<Map<String,Object>> salayUptoFive(String stockCode);
    ArrayList<Map<String,Object>> listStock(int id) throws IOException;
}

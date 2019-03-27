package com.seconds.kill.service;

import com.seconds.kill.pojo.Stock;

import java.util.List;

/**
 * 库存service
 * author:jinjin
 * Date:2019/3/22 20:05
 */
public interface StockService {

    /**
     * 获取当前库存
     * @return
     * @throws Exception
     */
    Integer getCurrentCount() throws Exception;

    List<Stock> getStockList();

    Integer getStock(int sid);
}

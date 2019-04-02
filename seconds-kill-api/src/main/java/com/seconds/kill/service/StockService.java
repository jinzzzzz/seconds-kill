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
     * 获取库存列表
     */
    List<Stock> getStockList();

    /**
     * 获取库存并写入缓存
     */
    Integer getStock(int sid);
}

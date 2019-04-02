package com.seconds.kill.service.impl;

import com.seconds.kill.api.constant.RedisKeys;
import com.seconds.kill.mapper.StockMapper;
import com.seconds.kill.pojo.Stock;
import com.seconds.kill.pojo.StockExample;
import com.seconds.kill.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * 库存业务
 * author:jinjin
 * Date:2019/3/22 20:24
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StockMapper stockMapper;


    @Override
    public List<Stock> getStockList(){
        return stockMapper.selectByExample(new StockExample());
    }

    @Override
    public Integer getStock(int sid) {
        String value = redisTemplate.opsForValue().get(RedisKeys.STOCK_COUNT + sid);
        if(null == value){
            Stock stock = stockMapper.selectByPrimaryKey(sid);
            Integer count=stock.getCount();
            redisTemplate.opsForValue().set(RedisKeys.STOCK_COUNT + sid,count.toString());
            redisTemplate.opsForValue().set(RedisKeys.STOCK_SALE + sid,"0");
            return count;
        }
        return 0;
    }
}

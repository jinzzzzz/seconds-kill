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
    public Integer getCurrentCount() throws Exception {
        String count = redisTemplate.opsForValue().get(RedisKeys.STOCK_COUNT + 1);
        if (count == null) {
            Stock stock = stockMapper.selectByPrimaryKey(1);
            count = stock.getCount().toString() ;
            redisTemplate.opsForValue().set(RedisKeys.STOCK_COUNT + 1, stock.getCount().toString());
            redisTemplate.opsForValue().set(RedisKeys.STOCK_SALE + 1, stock.getSale().toString());
            redisTemplate.opsForValue().set(RedisKeys.STOCK_VERSION + 1, stock.getVersion().toString());
        }

        return Integer.parseInt(count);
    }

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
            redisTemplate.opsForValue().set(RedisKeys.STOCK_VERSION + stock.getId(),"0") ;
            return count;
        }
        return 0;
    }
}

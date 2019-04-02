package com.seconds.kill.service.impl;

import com.seconds.kill.api.constant.RedisKeys;
import com.seconds.kill.mapper.StockOrderMapper;
import com.seconds.kill.mapper.StockMapper;
import com.seconds.kill.pojo.Stock;
import com.seconds.kill.pojo.StockOrder;
import com.seconds.kill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单业务
 * author:jinjin
 * Date:2019/3/22 20:20
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockOrderMapper orderMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate ;


    @Value("${kafka.topic}")
    private String kafkaTopic ;

    @Override
    public int placeOrder(int sid) throws Exception{

        //校验库存
        Stock stock = checkStockByRedis(sid);

        saleStock(stock);

        //创建订单
        int id = createOrder(stock);

        return id;
    }

    //从缓存校验库存，并使用redis decr命令递减库存
    private Stock checkStockByRedis(int sid) throws Exception {
        //自增
        Integer sale=redisTemplate.opsForValue().increment(RedisKeys.STOCK_SALE + sid,1).intValue();
        //递减
        Long count =  redisTemplate.opsForValue().decrement(RedisKeys.STOCK_COUNT + sid);
        if (null!=count&&count < 0){
            throw new RuntimeException("库存不足") ;
        }
        Stock stock = new Stock() ;
        stock.setId(sid);
        stock.setSale(sale);
        return stock;
    }

    //从数据库校验库存
    private Stock checkStock(int sid) {
        Stock stock = stockMapper.selectByPrimaryKey(sid);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }


    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int id = orderMapper.insertSelective(order);
        return id;
    }

    //更新数据库
    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

}

package com.seconds.kill.common;

import com.alibaba.fastjson.JSONObject;
import com.seconds.kill.api.constant.RedisKeys;
import com.seconds.kill.mapper.StockMapper;
import com.seconds.kill.mapper.StockOrderMapper;
import com.seconds.kill.pojo.Stock;
import com.seconds.kill.pojo.StockOrder;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka处理类
 * author:jinjin
 * Date:2019/4/2 20:16
 */
@Component
public class KafkaHandler {


    public static final String kafkaTopic = "SECONDS-KILL";

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockOrderMapper orderMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate ;

    private Logger logger = LoggerFactory.getLogger(KafkaHandler.class);

    /**
     * 消费日志消息
     * @param record
     */
    @KafkaListener(topics = kafkaTopic)
    public void listen(ConsumerRecord<String, String> record){
        try {
            if (record.value() != null) {
                String json = record.value();
                if(StringUtils.isNotEmpty(json)) {
                    Stock stock =JSONObject.parseObject(json,Stock.class);
                    //更新库存
                    saleStock(stock);
                    //创建订单
                    createOrder(stock);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    //更新数据库
    private int saleStock(Stock stock) {
        Integer sale=redisTemplate.opsForValue().increment(RedisKeys.STOCK_SALE + stock.getId()).intValue();
        stock.setSale(sale);
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    //创建订单
    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        int id = orderMapper.insertSelective(order);
        return id;
    }
}

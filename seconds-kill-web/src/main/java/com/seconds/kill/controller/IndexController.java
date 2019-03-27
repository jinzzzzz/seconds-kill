package com.seconds.kill.controller;

import com.seconds.kill.pojo.Stock;
import com.seconds.kill.service.OrderService;
import com.seconds.kill.service.StockService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 秒杀控制器
 * author:jinjin
 * Date:2019/3/22 21:15
 */
@RestController
@RequestMapping
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference
    private StockService stockService;

    @Reference
    private OrderService orderService;


    @GetMapping("/getStockList")
    public List<Stock> getStockList() {
        return stockService.getStockList();
    }

    /**
     * 手动获取库存 将数据写入缓存
     * @param sid 商品id
     * @return 库存
     */
    @GetMapping("/getStock/{sid}")
    public Integer getStock(@PathVariable int sid) {
        return stockService.getStock(sid);
    }

    @GetMapping("/placeOrder/{sid}")
    public String createWrongOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.placeOrder(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return "success";
    }
}

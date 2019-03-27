package com.seconds.kill.service;

/**
 * 订单service
 * author:jinjin
 * Date:2019/3/22 20:04
 */
public interface OrderService {

    /**
     * 创建订单
     * @param sid 库存ID
     * @return 订单ID
     */
    int placeOrder(int sid) throws Exception;

}

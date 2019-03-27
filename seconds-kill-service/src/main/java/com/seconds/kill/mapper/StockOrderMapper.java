package com.seconds.kill.mapper;

import com.seconds.kill.pojo.StockOrder;
import com.seconds.kill.pojo.StockOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单mapper
 * author:jinjin
 * Date:2019/3/22 20:18
 */
public interface StockOrderMapper {
    int countByExample(StockOrderExample example);

    int deleteByExample(StockOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    List<StockOrder> selectByExample(StockOrderExample example);

    StockOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockOrder record, @Param("example") StockOrderExample example);

    int updateByExample(@Param("record") StockOrder record, @Param("example") StockOrderExample example);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}

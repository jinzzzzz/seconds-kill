package com.seconds.kill.mapper;

import com.seconds.kill.pojo.Stock;
import com.seconds.kill.pojo.StockExample;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存mapper
 * author:jinjin
 * Date:2019/3/22 20:15
 */
public interface StockMapper {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    int updateByOptimistic(Stock record) ;
}

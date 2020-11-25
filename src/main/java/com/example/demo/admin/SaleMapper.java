package com.example.demo.admin;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SaleMapper {
    @Select("select * from sale_info")
    List<sale_info>findAll();

    @Select("select * from sale_info where goods_ids=#{goods_ids}")
    sale_info findSaleGood(int goods_ids);

    @Update("update sale_info set num=#{num} where id=#{id}")
    int alterNum(Integer num,Integer id);

    @Update("update sale_info set price=#{price} where id=#{id}")
    int alterPrice(Float price,Integer id);

    @Insert("insert into sale_info(goods_ids,goods_name,category,price,num) values(#{goods_ids},#{goods_name},#{category},#{price},#{num})")
    int insertSaleInfo(sale_info sale_info);
}

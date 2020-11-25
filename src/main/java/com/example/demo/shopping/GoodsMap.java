package com.example.demo.shopping;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMap {

    @Select("select * from goods_info where num>0")
    List<goods_info>find_clothes();

    @Select("select * from goods_info order by category")
    List<goods_info>find_all();

    @Select("select * from goods_info where goods_id=#{goods_id}")
    goods_info findById(Integer goods_id);

    @Insert("insert into goods_info(goods_name,price,num,category) values(#{goods_name},#{price},#{num},#{category})")
    int insert_info(goods_info info);

    @Update("update goods_info set price=#{price} where goods_id=#{goods_id}")
    int updatePrice(float price,int goods_id);

    @Update("update goods_info set num=#{num} where goods_id=#{goods_id}")
    int updateNum(int num,int goods_id);

    @Delete("delete from goods_info where goods_id=#{goods_id}")
    int deleteInfo(Integer goods_id);



}

package com.example.demo.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "userDao")
public interface UserDao {
    //登录功能接口
    public User Login(@Param("user_name")String user_name,
                      @Param("user_password")String user_password);

    public int Register(@Param("user_name")String user_name,
                         @Param("user_password") String user_password,
                         @Param("user_email")String user_email);

    public User SearchById(@Param("id")Integer id);

    public int UpdateGoods(@Param("goods_ids")String goods_ids,@Param("id")Integer id);

    public int SetNull(@Param("id") Integer id);




//    public int Register(@Param("email")String email);
}

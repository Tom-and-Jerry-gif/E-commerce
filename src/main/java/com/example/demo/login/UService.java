package com.example.demo.login;

public interface UService {
    public User login(String user_name,String user_password);
    public int register(String user_name,String user_password,String user_email);
    public User searchById(Integer id);
    public int updateGoodsId(String goods_ids ,Integer id);
    public int setNull(Integer id);

//    public int register(String email);
}

package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UService")
public class UserService implements UService{
    @Autowired
    private UserDao userDao;


    @Override
    public User login(String user_name, String user_password) {
        return userDao.Login(user_name,user_password);
    }


    @Override
    public int register(String user_name, String user_password, String user_email) {
        return userDao.Register(user_name,user_password,user_email);
    }

    @Override
    public User searchById(Integer id) {
        return userDao.SearchById(id);
    }

    @Override
    public int updateGoodsId(String goods_ids,Integer id) {
        return userDao.UpdateGoods(goods_ids,id);
    }

    @Override
    public int setNull(Integer id) {
        return userDao.SetNull(id);
    }


}

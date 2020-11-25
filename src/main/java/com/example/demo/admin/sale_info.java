package com.example.demo.admin;

import javax.persistence.*;

//@Entity

public class sale_info {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int goods_ids;
    private String goods_name;
    private String category;
    private float price;
    private int num;

    public int getId() {
        return id;
    }



    public int getGoods_ids() {
        return goods_ids;
    }



    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setGoods_ids(int goods_ids) {
        this.goods_ids = goods_ids;

    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

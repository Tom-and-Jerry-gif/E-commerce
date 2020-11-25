package com.example.demo.admin;

import com.example.demo.shopping.GoodsMap;
import com.example.demo.shopping.goods_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AddController {

    @Autowired(required = false)
    GoodsMap goodsMap;
    @GetMapping("/add")
    public String addPageShow(){
        return "add_goods";
    }

    @PostMapping("/add")
    public String add(@RequestParam("goodName")String goodName,
                      @RequestParam("category")String category, @RequestParam("price")
                      String price, @RequestParam("num")String num, Model model){
        goods_info good=new goods_info();
        good.setGoods_name(goodName);
        good.setCategory(category);
        good.setNum(Integer.parseInt(num));
        good.setPrice(Float.parseFloat(price));
        int k=goodsMap.insert_info(good);
        if(k>0){
            model.addAttribute("msg","添加成功！");
            return "redirect:/management";
        }else {
            model.addAttribute("msg","添加失败，请重试！");
            return "add_goods";
        }


    }
}

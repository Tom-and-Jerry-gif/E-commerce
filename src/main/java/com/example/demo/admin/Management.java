package com.example.demo.admin;

import com.example.demo.shopping.GoodsMap;
import com.example.demo.shopping.goods_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Management {

    @Autowired(required = false)
    GoodsMap goodsMap;

    @RequestMapping("/management")
    public String magPageShow(Model model){
        List<goods_info>info=goodsMap.find_all();
        model.addAttribute("goodInfo",info);

        return "goods_info_management";
    }
    @PostMapping("/management/{id}")
    public String deleteGood(@PathVariable("id") String id){
        goodsMap.deleteInfo(Integer.parseInt(id));
        return "redirect:/management";
    }


}

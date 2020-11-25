package com.example.demo.admin;

import com.example.demo.shopping.GoodsMap;
import com.example.demo.shopping.goods_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlterController {
    @Autowired(required = false)
    GoodsMap goodsMap;

    @Autowired(required = false)
    SaleMapper saleMapper;

    @GetMapping("/alter/{id}")
    public String alterPage(@PathVariable("id") String id, Model model){
        goods_info info=goodsMap.findById(Integer.parseInt(id));
        model.addAttribute("info",info);
        return "alter_goods";
    }




    @PostMapping("/alter/{id}")
    public  String alterSubmit(@PathVariable("id") String id,
                               @RequestParam("price")String price,
                               @RequestParam("num")String num,Model model){

        if(price==null||price==""||num==null||num==""){
            goods_info info=goodsMap.findById(Integer.parseInt(id));
            model.addAttribute("info",info);
            model.addAttribute("tips","单价/数量不能为空！");
//            model.addAttribute("id",id);
            return "alter_goods";
        }
        System.out.println(id);
        System.out.println(price);
        System.out.println(num);
        goods_info good=new goods_info();
        good.setNum(Integer.parseInt(num));
        good.setPrice(Float.parseFloat(price));
        good.setGoods_id(Integer.parseInt(id));
        int a=goodsMap.updateNum(Integer.parseInt(num),Integer.parseInt(id));
        if(a>0){
            int b=goodsMap.updatePrice(Float.parseFloat(price),Integer.parseInt(id));
            if(b>0){
                sale_info sinfo=saleMapper.findSaleGood(Integer.parseInt(id));
                if(sinfo!=null){
                    saleMapper.alterPrice(Float.parseFloat(price),sinfo.getId());
                }
                return "redirect:/management";
            }
        }

        return "/alter/"+id;




    }
}

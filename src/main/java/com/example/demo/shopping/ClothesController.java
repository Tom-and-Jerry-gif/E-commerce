package com.example.demo.shopping;

import com.example.demo.admin.SaleMapper;
import com.example.demo.admin.sale_info;
import com.example.demo.login.User;
import com.example.demo.login.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class ClothesController {

    private int userID;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());





    @Autowired(required = false)
    GoodsMap goodsMap;

    @Autowired
    UserService service;

    @Autowired(required = false)
    private SaleMapper info;

    @Autowired
    private IMailService mailService;


    @GetMapping("/clothes")
    public String Show(Model model,String id,User user){
        List<goods_info>info=goodsMap.find_clothes();
        model.addAttribute("clothes",info);
        userID=Integer.parseInt(id);
        user=service.searchById(userID);
        model.addAttribute("username",user.getUser_name());
        String goods=user.getGoods_ids();


        if(goods!=null){
            String[]goodList=goods.split("#");
            model.addAttribute("buyNum",String.valueOf(goodList.length));
        }
        logger.info(user.getUser_name()+"浏览商品。");
        return "clothes_page";
    }

    @PostMapping("/clothes/{good_id}")
    public String addShopCar(@PathVariable("good_id") String good_id,Model model, Map<String, Object> map){
        map.put("id",userID);
        int gID=Integer.parseInt(good_id);
        goods_info g=goodsMap.findById(gID);
        int a1=goodsMap.updateNum(g.getNum()-1,gID);


//            System.out.println("good ok");

            User u=service.searchById(userID);
            boolean flag=false;

            String goods=u.getGoods_ids();
            int a3=-1;
            if(goods!=null){
                String[]list1=goods.split("#");
                for(int i=0;i<list1.length;i++){
                    String[]list2=list1[i].split("-");
                    if(Integer.parseInt(list2[0])==gID){
                        int m=Integer.parseInt(list2[1])+1;
                        String now=list2[0]+"-"+String.valueOf(m);
                        String res=goods.replace(list1[i],now);
                        System.out.println("res:"+res);
                        a3=service.updateGoodsId(res,userID);
                        flag=true;
                        break;

                    }

                }
                if(flag==false){
                    String tail=gID+"-1#";
                    goods=goods+tail;
                    System.out.println("goods:"+goods);
                    a3=service.updateGoodsId(goods,userID);
                }
            }else {
                String t=gID+"-1#";
                System.out.println("t:"+t);
                a3=service.updateGoodsId(t,userID);
            }

            if(a3>0){
                System.out.println("user ok");
                logger.info(u.getUser_name()+"成功将"+g.getGoods_name()+"加入购物车。");
                return "redirect:/pay";
            }



        return "redirect:/clothes?id="+userID;



    }

    @GetMapping("/pay")
    public String show(Map<String, Object> map,User user,Model model){
        map.put("id",userID);
        user=service.searchById(userID);
        String goods=user.getGoods_ids();

        if(goods!=null){
            String[]goodList=goods.split("#");
            List<goods_info>goodsInfoList = new ArrayList<goods_info>();
            List<Integer>numList = new ArrayList<Integer>();
            List<Integer>countList = new ArrayList<Integer>();

            for(int i=0;i<goodList.length;i++){
                String[]buy=goodList[i].split("-");
                int goodID=Integer.parseInt(buy[0]),goodNum=Integer.parseInt(buy[1]);
                goods_info goods_info= goodsMap.findById(goodID);
                goodsInfoList.add(goods_info);
                numList.add(goodNum);
                countList.add(i);

            }
            model.addAttribute("goodsInfo",goodsInfoList);
            model.addAttribute("goodsNum",numList);
            model.addAttribute("count",countList);
        }
        return "shopping_car";
    }

    @PostMapping("/pay")
    public String payMoney(Model model,Map<String,Object>map){
        map.put("id",userID);
        User u=service.searchById(userID);
        String goods=u.getGoods_ids();
        if(goods!=null){
            String[]goodList=goods.split("#");
            for(int i=0;i<goodList.length;i++){
                String[] gnote=goodList[i].split("-");
                int gid=Integer.parseInt(gnote[0]);
                sale_info s=info.findSaleGood(gid);
                goods_info g=goodsMap.findById(gid);
                if(s!=null){
                    int k=info.alterNum(s.getNum()+Integer.parseInt(gnote[1]),s.getId());
                }else {
                    sale_info note=new sale_info();
                    note.setGoods_ids(gid);
                    note.setGoods_name(g.getGoods_name());
                    note.setCategory(g.getCategory());
                    note.setPrice(g.getPrice());
                    note.setNum(Integer.parseInt(gnote[1]));
                    int m=info.insertSaleInfo(note);
                }
                goods_info ginfo=goodsMap.findById(gid);
                int z=goodsMap.updateNum(ginfo.getNum()-Integer.parseInt(gnote[1]),gid);
                System.out.println("update num ok");
            }
            System.out.println("insert sale ok!");
            int r=service.setNull(userID);
            System.out.println("clear ok");
            mailService.sendSimpleMail(u.getUser_email(),"主题：订单确认邮件","您已成功下单商品！");
            logger.info(u.getUser_name()+"付款成功。");
            return "redirect:/clothes";


        }else {
            model.addAttribute("tips","用户购物车不能为空！");
            return "shopping_car";
        }




    }
}

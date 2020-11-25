package com.example.demo.login;

import org.apache.juli.logging.Log;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

//    private final Logger log= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UService uService;

    @GetMapping("/login")
    public String returnPage(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,User u,
                        Map<String,Object>map, HttpSession session){
        session.setAttribute("user",username);
        if(username.equals("admin")&&password.equals("111111")){
            map.put("name",username);
            return "redirect:/chart1";
        }else {
            u=uService.login(username,password);
//            System.out.println(u.getUser_email());
//            System.out.println(u.getUser_password());
//            System.out.println(u.getGoods_ids());
//            System.out.println(u.getUser_name());
            if(u!=null){
//                map.put("name",username);
//                map.put("buy",u.getGoods_ids());
                map.put("id",u.getId());
                return "redirect:/clothes";

            }else {
                map.put("msg","用户名或密码不存在！");


                return "login";
            }
        }

    }

}

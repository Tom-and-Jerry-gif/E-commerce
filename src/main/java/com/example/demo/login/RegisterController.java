package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UService uService;

    @GetMapping("/register")
    public String jump_register(){
        return "register";
    }

    @PostMapping("/register")
//    public String register(@RequestParam("email")String email){
//        int x=uService.register(email);
//        return "register";
//    }


    public String register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("email")String email,User u,Map<String,Object>map){
        if(username==null||username==""||password==null||password==""||email==null||email==""){
            map.put("msg2","信息填写不完全无法提交！");
            return "register";
        }

        u=uService.login(username,password);
        if(u!=null){
            map.put("msg2","用户账号已经存在，请直接登录");
            return "register";
        }else {
            int x=uService.register(username,password,email);
            if(x>0){
                map.put("msg2","注册成功！");
                return "redirect:/login";
            }else{
                map.put("msg2","用户账号注册失败，请重新注册！");
                return "register";
            }

        }


    }
}

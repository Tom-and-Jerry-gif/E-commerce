package com.example.demo.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SaleInfoShowController {

    @Autowired(required = false)
    private SaleMapper info;

    @RequestMapping("/query")
    @ResponseBody
    public List queryAll(){
        return  info.findAll();
    }

    @RequestMapping("/chart1")
    public String Show(Model model){
        model.addAttribute("info",info.findAll());
        return "chart1";
    }


}

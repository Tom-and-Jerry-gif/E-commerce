package com.example.demo.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LogController {

    private String path;


    @RequestMapping("/logs")
    public String logsShow(Model model) throws IOException {
        List<String>logsList=new ArrayList<String>();
        path="./logs/spring.log";
        FileInputStream fstream = new FileInputStream(new File(path));
        //构造一个InputStream对象，这个对象是字节流通向字符流的桥梁，它读取字节并将其解码为字符
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));//构造一个BufferedReader，里面存放在控制台输入的字节转换后成的字符。
        String strLine;
        while((strLine = br.readLine()) != null){
//            strLine = br.readLine();//一行一行读取数据
            if((strLine.indexOf("成功")>0)||(strLine.indexOf("商品")>0)){
                strLine=strLine.replace("c.e.demo.shopping.ClothesController","");
                logsList.add(strLine);
            }
        }
        model.addAttribute("logs",logsList);
        br.close();
        fstream.close();

        return "logs";
    }
}

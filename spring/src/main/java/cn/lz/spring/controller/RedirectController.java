package cn.lz.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("redirect/{url}")
    public String redirect(@PathVariable String url) {
        System.out.println("url " + url);
        // view resolve原理
        return "redirect:http://baidu.com";
    }

}

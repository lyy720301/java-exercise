package cn.lz.spring.controller;

import cn.lz.spring.po.UserPo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 本Controller下实现跨域
 */
@CrossOrigin({"http://localhost:8080"})
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello, world!";
    }

    @PostMapping("/hello")
    public String hello(@RequestBody UserPo userPo) {
        return "hello, " + userPo;
    }

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name, HttpServletRequest request) {
        return "hello, " + name;
    }

    @PostMapping("/postHello")
    public String postHello(@RequestParam("name") String name) {

        return "post hello , name=" + name;
    }
}

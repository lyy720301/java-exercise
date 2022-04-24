package cn.lz.spring.controller;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/postHello")
    public String postHello(@RequestParam("name") String name) {

        return "post hello , name=" + name;
    }
}

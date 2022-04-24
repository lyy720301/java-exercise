package cn.lz.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;

@RequestMapping("/study")
@RestController
public class StudyController {

    @RequestMapping("/hello")
    public String study() throws BindException {
        if (1 == 1)
            throw new BindException("hello");
        return "hello, world!";
    }
}

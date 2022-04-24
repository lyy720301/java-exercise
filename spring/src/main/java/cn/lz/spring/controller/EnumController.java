package cn.lz.spring.controller;

import cn.lz.spring.entity.Human;
import cn.lz.spring.entity.Sex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 传枚举值
 */
@RestController
@RequestMapping("/enum")
public class EnumController {

    @RequestMapping("/human")
    public Human getHuman() {
        Human human = new Human();
        human.setName("lz");
        human.setSex(Sex.MAN);
        return human;
    }
}

package cn.lz.spring.controller;

import cn.lz.spring.entity.Human;
import cn.lz.spring.util.ValidListNew;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("valid")
public class ValidController {

    /**
     * 可以进行分组校验，<s>但是不能校验humans列表长度为0的情况</s><br/>
     * <b>是可以校验长度为0的情况的 @ValidListNew ，在@NotEmpty中加上group属性就可以进行校验</b>
     */
    @PostMapping("testValid")
    public String testValid(@Validated(Insert.class) @RequestBody ValidListNew<Human> humans, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        log.info("请求入参{}", humans);
        return "参数校验通过";
    }

    /**
     * 可以校验humans列表长度为0的情况，但是不能分组
     */
    @PostMapping("testValidWithoutBinding")
    public String testValidWithoutBinding(@Valid @RequestBody ValidListNew<Human> humans, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("params error:{}", bindingResult.getAllErrors());
            return (bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.info("请求入参{}", humans);
        return "参数校验通过";
    }
}

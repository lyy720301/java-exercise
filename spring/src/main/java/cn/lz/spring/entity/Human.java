package cn.lz.spring.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import javax.validation.constraints.NotBlank;

@Data
public class Human {

    @NotBlank(message = "name不得为空")
    private String name;

    @NotBlank(groups = Insert.class, message = "description不得为空")
    private String description;

    private Sex sex;
}

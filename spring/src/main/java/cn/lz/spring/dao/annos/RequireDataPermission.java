package cn.lz.spring.dao.annos;

import cn.lz.spring.constants.PowerConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequireDataPermission {

    //需要拦截的sql字段
    String value();

    //根据用户的哪个信息进行拦截
    int prop() default PowerConstant.PLANT;
}

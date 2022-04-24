package cn.lz.spring.util;

import lombok.Data;
import lombok.experimental.Delegate;
import org.apache.ibatis.annotations.Insert;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * 比较好的一种校验List方式
 *
 * @param <E>
 */
@Data
public class ValidListNew<E> implements List<E> {

    @Valid
    @Delegate
    @NotEmpty(message = "empty data list", groups = {Default.class, Insert.class})
    private List<E> list = new ArrayList<>();

    @Override
    public String toString() {
        return list.toString();
    }
}
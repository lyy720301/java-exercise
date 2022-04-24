package cn.lz.spring.util;

import com.google.common.collect.ForwardingList;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidList<T> extends ForwardingList<T> {

    private List<@Valid T> list;

    public ValidList() {
        this(new ArrayList<>());
    }

    public ValidList(List<@Valid T> list) {
        this.list = list;
    }

    @Override
    protected List<T> delegate() {
        return list;
    }

    /**
     * Exposed for the {@link javax.validation.Validator} to access the list path
     */
    public List<T> getList() {
        return list;
    }
}
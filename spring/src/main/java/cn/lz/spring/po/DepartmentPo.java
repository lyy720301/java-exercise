package cn.lz.spring.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPo {
    private Integer id;

    private String name;

    private String code;
}
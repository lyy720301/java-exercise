package cn.lz.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Order implements Serializable {

    private Long id;

    private LocalDateTime date;

    private String description;
}

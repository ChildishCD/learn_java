package com.javasm.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member implements Serializable {

    private static final long serialVersionUID = -8134976193493018736L;
    private Integer id;
    private String name;
    private String pass;
    private String image;
    private String phone;
    private float point;
    private BigDecimal balance;
    private Date createTime;
    private Date updateTime;

}

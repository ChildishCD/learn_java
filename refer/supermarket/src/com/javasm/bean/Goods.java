package com.javasm.bean;


import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Accessors(chain = true)
@Builder
public class Goods implements Serializable {
    private static final long serialVersionUID = 5931727568081086539L;
    private Integer id;
    private String goodName;
    private Integer typeId;
    private Integer store;
    private Integer goodStatus;
    private float discount;
    private float price;
    private Date createTime;
    private Date updateTime;


}

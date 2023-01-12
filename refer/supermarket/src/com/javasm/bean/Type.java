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
@Builder//链式调用方法
public class Type implements Serializable {

    private static final long serialVersionUID = 4137598174056104297L;
    private Integer id;
    private Integer fid;
    private String typeName;
    private String isParent;
    private String typeStatus;
    private Date createTime;
    private Date updateTime;

}

package com.javasm.bean;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -9191889573319884549L;
    private Integer id;
    private Integer oid;
    private Integer gid;
    private Integer buyNum;

}

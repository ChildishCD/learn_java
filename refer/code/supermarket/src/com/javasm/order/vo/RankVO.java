package com.javasm.order.vo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class RankVO {

    private Integer id;//商品id
    private String name;//商品名称
    private Integer num;//销售数量
    private String type;//商品类别

}

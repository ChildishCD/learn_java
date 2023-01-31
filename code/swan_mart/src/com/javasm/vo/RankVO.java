package com.javasm.vo;

import com.javasm.base.BaseVO;
import com.javasm.bean.MemberOrdersModel;
import lombok.*;

import java.lang.annotation.Target;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RankVO extends BaseVO<MemberOrdersModel> {
    private Integer itsRank;//排名
    private Integer id;//商品id
    private String name;//商品名称
    private String type;//商品类别
    private Integer num;//销售数量
}

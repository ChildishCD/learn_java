package com.javasm.bean;


import com.javasm.annotation.Column;
import com.javasm.annotation.ID;
import com.javasm.annotation.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("member_orders")
public class MemberOrdersModel implements Serializable, Comparable<MemberOrdersModel> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Column("id")
    @ID("id")
    private Integer id;

    /**
     * 会员ID
     */
    @Column("member_id")
    private Integer memberId;

    /**
     * 总金额
     */
    @Column("price_total")
    private Double priceTotal;

    /**
     * 下单时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 支付类型（0-现金，1-余额）
     */
    @Column("payment_type")
    private Byte paymentType;


    @Override
    public int compareTo(MemberOrdersModel o) {
        return id - o.id;
    }

    public String getPayTypeStr(){
        //0现金1余额
        if (this.paymentType == 0){
            return "现金";
        }else if (this.paymentType == 1){
            return "会员";
        }
        return "未知";
    }
}

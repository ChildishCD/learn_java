package com.javasm.bean;


import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("member_orders")
public class MemberOrdersModel implements Serializable{

	private static final long serialVersionUID =  1L;

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


}

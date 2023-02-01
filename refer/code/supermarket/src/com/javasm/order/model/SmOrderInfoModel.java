package com.javasm.order.model;


import lombok.*;
import java.io.Serializable;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_order_info")
public class SmOrderInfoModel implements Serializable{

	private static final long serialVersionUID =  1L;

	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 订单id
	 */
	@Column("order_id")
	private Integer orderId;

	/**
	 * 商品id
	 */
	@Column("goods_id")
	private Integer goodsId;

	/**
	 * 购买商品数量
	 */
	@Column("goods_num")
	private Integer goodsNum;


}

package com.javasm.bean;


import lombok.*;
import java.io.Serializable;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("member_orders_products")
public class MemberOrdersProductsModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 订单详情ID
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 订单ID
	 */
	@Column("order_id")
	private Integer orderId;

	/**
	 * 商品ID
	 */
	@Column("product_id")
	private Integer productId;

	/**
	 * 商品数量
	 */
	@Column("count")
	private Integer count;


}

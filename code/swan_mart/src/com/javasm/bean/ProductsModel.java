package com.javasm.bean;


import lombok.*;
import java.io.Serializable;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("products")
public class ProductsModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 商品编号
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 商品名
	 */
	@Column("name")
	private String name;

	/**
	 * 商品所属类型id
	 */
	@Column("type_id")
	private Integer typeId;

	/**
	 * 商品库存数量
	 */
	@Column("inventory")
	private Integer inventory;

	/**
	 * 单价
	 */
	@Column("price")
	private Double price;

	/**
	 * 商品状态（ 1-正常，2-下架，3-删除）
	 */
	@Column("state")
	private Byte state;

	/**
	 * 折扣（默认是0.9）
	 */
	@Column("discount")
	private Double discount;

	/**
	 * 商品规格
	 */
	@Column("specs")
	private String specs;


}

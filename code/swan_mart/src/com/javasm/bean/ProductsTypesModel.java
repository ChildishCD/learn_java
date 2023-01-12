package com.javasm.bean;


import lombok.*;
import java.io.Serializable;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("products_types")
public class ProductsTypesModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 商品类型ID
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 父级商品类型ID
	 */
	@Column("pid")
	private Integer pid;

	/**
	 * 是否为父类型（1为是，0为否）
	 */
	@Column("isparent")
	private Byte isparent;

	/**
	 * 商品类型名称
	 */
	@Column("name")
	private String name;


}

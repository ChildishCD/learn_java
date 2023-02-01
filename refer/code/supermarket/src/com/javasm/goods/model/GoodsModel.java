package com.javasm.goods.model;


import com.javasm.base.BaseModel;
import lombok.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_goods")//标记了当前的类，给当前类一个属性  不会修改类的值，等着其他位置去调用
public class GoodsModel extends BaseModel implements Serializable,Cloneable{

	private static final long serialVersionUID =  1L;

	@Override
	public GoodsModel clone() {
		try {
			return (GoodsModel) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 商品编号
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 商品名称
	 */
	@Column("goods_name")
	private String goodsName;

	/**
	 * 商品类型
	 */
	@Column("goods_type")
	private Integer goodsType;

	@Column("price")
	private Double price;

	@Column("num")
	private Integer inventory;

	/**
	 * 1正常2下架3删除
	 */
	@Column("state")
	private Integer state;

	/**
	 * 折扣
	 */
	@Column("discount")
	private Integer discount;

	/**
	 * 是否删除  0否 1是
	 */
	@Column("is_del")
	private Integer isDel;

	//因为只想知道商品类型的名字，所以不做1对1关联了，只添加个类型名字的属性
	private String typeName;

	public String getStateStr() {
		if (this.state == 1){
			return "正常";
		}else if (this.state == 2){
			return "下架";
		}else if (this.state == 3){
			return "删除";
		}
		return "未知";
	}

	//商品类型
	//private GoodsTypeModel goodsTypeModel;


	public String getPriceStr() {
		NumberFormat numberFormat = new DecimalFormat(".##");
		return numberFormat.format(price);
	}

	public Double getRealPrice(){
		//真实价格 是price 打折
		return price * discount / 10;
	}
}

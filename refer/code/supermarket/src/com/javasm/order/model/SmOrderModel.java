package com.javasm.order.model;


import lombok.*;
import java.io.Serializable;;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_order")
public class SmOrderModel implements Serializable{

	public SmOrderModel(Integer memberId, Long rental, Integer payType) {
		this.memberId = memberId;
		this.rental = rental;
		this.payType = payType;
	}

	private static final long serialVersionUID =  1L;

	@Column("id")
	@ID("id")
	private Integer id;

	@Column("member_id")
	private Integer memberId;

	/**
	 * 总额（分）
	 */
	@Column("rental")
	private Long rental;

	/**
	 * 下单时间
	 */
	@Column("create_time")
	private LocalDateTime createTime;

	/**
	 * 0现金1余额
	 */
	@Column("pay_type")
	private Integer payType;

	private List<SmOrderInfoModel> orderInfoList;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SmOrderModel that = (SmOrderModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public String getTotalPrice(){
		double price = this.rental / 100.0;
		NumberFormat numberFormat = new DecimalFormat(".##");
		return numberFormat.format(price);
	}

	public String getPayTypeStr(){
		//0现金1余额
		if (this.payType == 0){
			return "现金";
		}else if (this.payType == 1){
			return "会员";
		}
		return "未知";
	}
}

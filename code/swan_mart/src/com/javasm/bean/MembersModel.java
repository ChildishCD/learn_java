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
@Table("members")
public class MembersModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 会员编号
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 会员名
	 */
	@Column("name")
	private String name;

	/**
	 * 密码
	 */
	@Column("password")
	private String password;

	/**
	 * 练习方式
	 */
	@Column("contact")
	private String contact;

	/**
	 * 积分
	 */
	@Column("points")
	private Double points;

	/**
	 * 余额
	 */
	@Column("balance")
	private Double balance;

	/**
	 * 创建时间
	 */
	@Column("create_time")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@Column("update_time")
	private LocalDateTime updateTime;


}

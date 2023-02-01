package com.javasm.member.model;


import lombok.*;
import java.io.Serializable;;
import java.time.LocalDateTime;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_member")
public class SmMemberModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 会员id
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 会员名称
	 */
	@Column("member_name")
	private String memberName;

	/**
	 * 密码
	 */
	@Column("password")
	private String password;

	/**
	 * 联系方式
	 */
	@Column("contact")
	private String contact;

	/**
	 * 积分
	 */
	@Column("integral")
	private Long integral;

	/**
	 * 余额（分）
	 */
	@Column("balance")
	private Long balance;

	@Column("create_time")
	private LocalDateTime createTime;

	@Column("update_time")
	private LocalDateTime updateTime;


}

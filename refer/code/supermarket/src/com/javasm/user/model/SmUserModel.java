package com.javasm.user.model;


import lombok.*;
import java.io.Serializable;;
import java.time.LocalDateTime;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_user")
public class SmUserModel implements Serializable{

	private static final long serialVersionUID =  1L;

	public SmUserModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Column("uid")
	@ID("uid")
	private Long uid;

	/**
	 * 用户名
	 */
	@Column("username")
	private String username;

	/**
	 * 密码
	 */
	@Column("password")
	private String password;

	/**
	 * 角色id
	 */
	@Column("role_id")
	private Integer roleId;

	/**
	 * 注册时间
	 */
	@Column("reg_time")
	private LocalDateTime regTime;

	/**
	 * 登录时间
	 */
	@Column("login_time")
	private LocalDateTime loginTime;

	/**
	 * =1有效 =0无效
	 */
	@Column("isvalid")
	private Integer isvalid;

	//用户角色信息
	private SmRoleModel role;


}

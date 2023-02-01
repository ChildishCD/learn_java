package com.javasm.user.model;


import lombok.*;
import java.io.Serializable;
import java.util.List;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_role")
public class SmRoleModel implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 角色id
	 */
	@Column("rid")
	@ID("rid")
	private Integer rid;

	@Column("role_name")
	private String roleName;

	@Column("remarks")
	private String remarks;

	//当前角色的 菜单信息
	private List<SmMenuModel> menuList;


}

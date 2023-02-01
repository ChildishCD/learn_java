package com.javasm.user.model;


import lombok.*;
import java.io.Serializable;;

import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_rel_role_menu")
public class SmRelRoleMenuModel implements Serializable{

	private static final long serialVersionUID =  1L;

	@Column("role_id")
	private Integer roleId;

	@Column("menu_id")
	private Integer menuId;


}

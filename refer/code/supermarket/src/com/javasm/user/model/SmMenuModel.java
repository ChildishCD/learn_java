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
@Table("sm_menu")
public class SmMenuModel implements Serializable{

	private static final long serialVersionUID =  1L;

	@Column("mid")
	@ID("mid")
	private Integer mid;

	/**
	 * 菜单名
	 */
	@Column("name")
	private String name;

	/**
	 * 父id
	 */
	@Column("pid")
	private Integer pid;

	/**
	 * 是否显示在导航,1显示,2不显示
	 */
	@Column("show")
	private Integer show;

	//二级菜单列表
	private List<SmMenuModel> childList;

}

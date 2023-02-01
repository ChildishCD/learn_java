package com.javasm.goods.model;


import lombok.*;
import java.io.Serializable;;
import java.time.LocalDateTime;
import java.util.List;


import com.javasm.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("sm_goods_type")
public class GoodsTypeModel implements Serializable{

	private static final long serialVersionUID =  1L;

	public GoodsTypeModel(Integer pid, String typeName) {
		this.pid = pid;
		this.typeName = typeName;
	}

	public GoodsTypeModel(Integer id, Integer pid, String typeName) {
		this.id = id;
		this.pid = pid;
		this.typeName = typeName;
	}

	/**
	 * 商品类型ID
	 */
	@Column("id")
	@ID("id")
	private Integer id;

	/**
	 * 父id，0表示一级目录
	 */
	@Column("pid")
	private Integer pid;

	@Column("type_name")
	private String typeName;

	@Column("create_time")
	private LocalDateTime createTime;

	@Column("update_time")
	private LocalDateTime updateTime;

	private List<GoodsTypeModel> childTypeList;

}

package jdbc.book.model;


import lombok.*;
import java.io.Serializable;;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChuangbieBookStype implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 分类ID
	 */
	private Integer typeId;
	/**
	 * 分类名称
	 */
	private String typeName;
	/**
	 * 1.男频 2.女频 3.出版
	 */
	private Byte attribution;
	/**
	 * 所属一级分类
	 */
	private Integer ftypeId;

	public ChuangbieBookStype(ResultSet rs) throws SQLException{
		this.typeId = rs.getInt("type_id");
		this.typeName = rs.getString("type_name");
		this.attribution = rs.getByte("attribution");
		this.ftypeId = rs.getInt("ftype_id");
	}
}

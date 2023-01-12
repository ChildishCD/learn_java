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
public class ChuangbieBookFtype implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 分类id
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

	public ChuangbieBookFtype(ResultSet rs) throws SQLException{
		this.typeId = rs.getInt("type_id");
		this.typeName = rs.getString("type_name");
		this.attribution = rs.getByte("attribution");
	}
}

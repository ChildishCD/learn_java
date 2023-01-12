package jdbc.user.model;


import lombok.*;
import java.io.Serializable;;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmMenu implements Serializable{

	private static final long serialVersionUID =  1L;

	private Integer mid;
	/**
	 * 菜单名
	 */
	private String name;
	/**
	 * 父id
	 */
	private Long pid;
	/**
	 * 是否显示在导航,1显示,2不显示
	 */
	private Integer show;

	public SmMenu(ResultSet rs) throws SQLException{
		this.mid = rs.getInt("mid");
		this.name = rs.getString("name");
		this.pid = rs.getLong("pid");
		this.show = rs.getInt("show");
	}
}

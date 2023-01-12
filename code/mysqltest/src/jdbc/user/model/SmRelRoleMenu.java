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
public class SmRelRoleMenu implements Serializable{

	private static final long serialVersionUID =  1L;

	private Integer roleId;
	private Integer menuId;

	public SmRelRoleMenu(ResultSet rs) throws SQLException{
		this.roleId = rs.getInt("role_id");
		this.menuId = rs.getInt("menu_id");
	}
}

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
public class SmRole implements Serializable{

	private static final long serialVersionUID =  1L;

	/**
	 * 角色id
	 */
	private Integer rid;
	private String roleName;
	private String remarks;

	public SmRole(ResultSet rs) throws SQLException{
		this.rid = rs.getInt("rid");
		this.roleName = rs.getString("role_name");
		this.remarks = rs.getString("remarks");
	}
}

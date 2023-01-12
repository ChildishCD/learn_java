package jdbc.user.model;


import lombok.*;
import java.io.Serializable;;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmUser implements Serializable{
	//关联用户详情
	private SmUserInfo smUserInfo;
	private SmRole smRole;

	private static final long serialVersionUID =  1L;

	private Long uid;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 注册时间
	 */
	private Date regTime;
	/**
	 * 登录时间
	 */
	private Date loginTime;
	/**
	 * =1有效 =0无效
	 */
	private Integer isvalid;

	public SmUser(ResultSet rs){
		try {
			this.uid = rs.getLong("uid");
			this.username = rs.getString("username");
			this.password = rs.getString("password");
			this.roleId = rs.getLong("role_id");
			this.regTime = rs.getTimestamp("reg_time");
			this.loginTime = rs.getTimestamp("login_time");
			this.isvalid = rs.getInt("isvalid");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

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
public class SmUserInfo implements Serializable{

	private static final long serialVersionUID =  1L;

	private Integer uid;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 性别0未知1男2女
	 */
	private Integer gender;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 星座
	 */
	private String seat;

	public SmUserInfo(ResultSet rs){
		try {
			this.uid = rs.getInt("uid");
			this.nickname = rs.getString("nickname");
			this.age = rs.getInt("age");
			this.address = rs.getString("address");
			this.gender = rs.getInt("gender");
			this.city = rs.getString("city");
			this.seat = rs.getString("seat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

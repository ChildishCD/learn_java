package jdbc.user.dao;

import jdbc.user.model.SmUserInfo;
import utils.JavasmDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmUserInfoDAO {
    //与选择用户详情的数据库操作
    public SmUserInfoDAO() {
    }
    //根据用户id查询用户详情
    public SmUserInfo selectSmUserByUid(int uid) {
        String sql = "select * from sm_user_info where uid = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        SmUserInfo userInfo = null;
        try {
            ps.setInt(1, uid);
            set = ps.executeQuery();
            if (set.next()) {
                userInfo = new SmUserInfo(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(set,ps);
        }
        return userInfo;
    }
}

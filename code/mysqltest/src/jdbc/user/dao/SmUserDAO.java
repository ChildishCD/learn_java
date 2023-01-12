package jdbc.user.dao;

import jdbc.user.model.SmUser;
import jdbc.user.model.SmUserInfo;
import utils.JavasmDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SmUserDAO {
    //与选择用户相关的数据库操作
    public SmUserDAO() {
    }
    //根据姓名模糊查询
    public List<SmUser> selectUserByNameLikely(String name){
        List<SmUser> smUsers = null;
        name = "%"+name+"%";
        String sql = "select * from sm_user,sm_user_info where sm_user.uid = sm_user_info.uid and sm_user_info.nickname like ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        try {
            ps.setString(1,name);
            set = ps.executeQuery();
            smUsers = new ArrayList<>();
            while (set.next()){
                SmUser smUser = new SmUser(set);
                smUser.setSmUserInfo(new SmUserInfo(set));
                smUsers.add(smUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(set,ps);
        }
        return smUsers;
    }
    //更新登录时间
    public void updateLoginTime(int uid){
        String sql = "update sm_user set login_time = NOW() where uid = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        try {
            ps.setInt(1,uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(ps);
        }
    }
    //根据登录信息查询用户
    public SmUser selectUserByLoginInfo(String name, String password) {
        String sql = "select * from sm_user where isvalid = 1 and username = ? and password = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        SmUser user = null;
        try {
            ps.setString(1, name);
            ps.setString(2, password);
            set = ps.executeQuery();
            if (set.next()) {
                user = new SmUser(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(set, ps);
        }
        return user;
    }
}

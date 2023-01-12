package jdbc.user.dao;

import com.mysql.cj.util.StringUtils;
import jdbc.user.model.SmRole;
import utils.JavasmDBUtil;
import utils.ParameterUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SmRoleDAO {
    public SmRoleDAO() {
    }
    public SmRole selectRoleById(long rid){
        SmRole smRole = null;
        String sql = "select * from sm_role where rid = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setLong(1,rid);
            rs = ps.executeQuery();
            rs.next();
            smRole = new SmRole(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JavasmDBUtil.close(rs,ps);
        }
        return smRole;
    }

    public SmRole selectRoleByName(String roleName) {
        SmRole smRole = null;
        String sql = "select * from sm_role where role_name = ?";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        try {
            ps.setString(1, roleName);
            set = ps.executeQuery();
            if (set.next()) {
                smRole = new SmRole(set);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(set, ps);
        }
        return smRole;
    }
}

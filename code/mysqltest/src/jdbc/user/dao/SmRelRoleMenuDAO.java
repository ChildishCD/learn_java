package jdbc.user.dao;

import jdbc.user.model.SmMenu;
import utils.JavasmDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SmRelRoleMenuDAO {
    public SmRelRoleMenuDAO() {
    }

    public int[] insertRel(Connection connection, int rid, List<SmMenu> midList) {
        int[] i = null;
        String sql = "insert into sm_rel_role_menu (role_id, menu_id) values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (SmMenu menu : midList) {
                ps.setInt(1, rid);
                ps.setInt(2, menu.getMid());
                ps.addBatch();
            }
            i = ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean deleteMenuByRid(Connection connection,int rid) {
        String sql = "delete from sm_rel_role_menu where role_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rid);
            int i = ps.executeUpdate();
            if ( i >= 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

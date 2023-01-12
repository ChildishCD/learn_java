package jdbc.user.dao;

import jdbc.user.model.SmMenu;
import utils.JavasmDBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SmMenuDAO {
    public SmMenuDAO() {
    }

    public Map<Integer, List<SmMenu>> getFullMenuByRoleId(long rid) {
        Map<Integer, List<SmMenu>> menuListMap = null;
        String sql = "SELECT DISTINCT m.*FROM sm_menu m,(SELECT m.*FROM sm_menu m,sm_role r,sm_rel_role_menu rel WHERE rel.menu_id=m.mid AND rel.role_id=r.rid AND r.rid= ? ) rm WHERE m.mid=rm.mid OR m.pid=rm.mid OR m.mid=rm.pid ORDER BY m.pid,m.mid";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setLong(1, rid);
            rs = ps.executeQuery();
            menuListMap = new TreeMap<>();
            while (rs.next()) {
                if (rs.getLong("pid") == 0) {
                    List<SmMenu> temp = new ArrayList<>();
                    temp.add(new SmMenu(rs));
                    menuListMap.put(rs.getInt("mid"), temp);
                } else {
                    menuListMap.get(rs.getInt("pid")).add(new SmMenu(rs));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(rs, ps);
        }
        return menuListMap;
    }

    public List<SmMenu> getFullMenuByMidString(String mids) {
//        Set<Integer> midSet = Arrays.stream(mids.split(",")).map(Integer::parseInt).collect(Collectors.toSet());
        List<SmMenu> midList = null;
        String sql = "SELECT DISTINCT t2.* FROM sm_menu t1,sm_menu t2 WHERE (t1.mid=t2.pid AND (t1.mid IN (" + mids + ") OR t2.mid IN (" + mids + "))) OR (t2.pid=0 AND t2.mid IN (" + mids + ")) ORDER BY t2.mid";
        PreparedStatement ps = JavasmDBUtil.getPreparedStatement(sql);
        ResultSet set = null;
        try {
//            ps.setString(1, mids);
//            ps.setString(2, mids);
            set = ps.executeQuery();
//            System.out.println(set.getStatement().toString());
            midList = new ArrayList<>();
            while (set.next()) {
                midList.add(new SmMenu(set));
            }
//            mids = midList.stream().map(String::valueOf).collect(Collectors.joining(","));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JavasmDBUtil.close(set, ps);
        }
        return midList;
    }
}

package jdbc.user.service;

import jdbc.user.dao.SmMenuDAO;
import jdbc.user.dao.SmRelRoleMenuDAO;
import jdbc.user.dao.SmRoleDAO;
import jdbc.user.model.SmMenu;
import jdbc.user.model.SmRole;
import jdbc.user.model.SmUser;
import utils.JavasmDBUtil;
import utils.ParameterUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SmRoleService {
    private SmRoleDAO smRoleDAO;
    private SmMenuDAO smMenuDAO;
    private SmRelRoleMenuDAO smRelRoleMenuDAO;

    public SmRoleService() {
        this.smRoleDAO = new SmRoleDAO();
        this.smMenuDAO = new SmMenuDAO();
        this.smRelRoleMenuDAO = new SmRelRoleMenuDAO();
    }

    public void setSmRoleForSmUser(SmUser smUser) {
        if (ParameterUtil.checkLegal(smUser) && !ParameterUtil.checkLegal(smUser.getSmRole())) {
            SmRole smRole = smRoleDAO.selectRoleById(smUser.getRoleId());
            if(ParameterUtil.checkLegal(smRole)) {
                smUser.setSmRole(smRole);
            }
        }
    }

    public Map<Integer, List<SmMenu>> getUserRoleMenus(SmUser smUser) {
        Map<Integer, List<SmMenu>> menuList = null;
        if(ParameterUtil.checkLegal(smUser)){
           menuList =  smMenuDAO.getFullMenuByRoleId(smUser.getRoleId());
        }
        return menuList;
    }

    public int[] changeRoleMenu(String roleName, String mids) {
        if (!ParameterUtil.checkLegal(roleName, mids) || !ParameterUtil.checkNumbers(mids)) return null;
        int rid = smRoleDAO.selectRoleByName(roleName).getRid();
        if (!ParameterUtil.checkLegal(rid)) return null;
//            System.out.println(rid);
        List<SmMenu> midList = smMenuDAO.getFullMenuByMidString(mids);
//            System.out.println(midList);
        //事务的回滚
        Connection connection = JavasmDBUtil.getConnection();
        int[] i = null;
        try {
            connection.setAutoCommit(false);
            if (!smRelRoleMenuDAO.deleteMenuByRid(connection, rid)) return null;
            i = smRelRoleMenuDAO.insertRel(connection, rid, midList);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
        return i;
//                System.out.println(Arrays.toString(i));
        //根据 roleName 查询 rid --> rid
        //根据 一级mid 查询 二级mid --> midList
        //根据 rid 删除 已有的权限 --> delete
        //根据 rid 和 midList 插入rel表 --> insert
    }
}

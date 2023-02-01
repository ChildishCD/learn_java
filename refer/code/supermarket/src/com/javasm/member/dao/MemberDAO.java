package com.javasm.member.dao;

import com.javasm.base.BaseDAO;
import com.javasm.member.model.SmMemberModel;
import com.javasm.utils.DBHelper;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class MemberDAO extends BaseDAO<SmMemberModel> {

    //不能两个方法同时修改余额
    public synchronized void pay(Integer id,Long money){
        String sql = "UPDATE sm_member SET balance = balance + ? WHERE id = ?";
        QueryRunner runner = DBHelper.getQueryRunner();
        try {
            runner.update(sql,money,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SmMemberModel checkMember(String name,String pass){
        String sql = "SELECT * FROM sm_member WHERE member_name = ? AND `password` = ?";
        return selectObject(sql,name,pass);
    }
}

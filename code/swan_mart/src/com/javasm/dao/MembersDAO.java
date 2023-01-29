package com.javasm.dao;

import com.javasm.base.BaseDAO;
import com.javasm.bean.MembersModel;
import com.javasm.bean.ProductsModel;

import java.util.List;

public class MembersDAO extends BaseDAO<MembersModel> {
    public MembersModel selectMemberById(Integer id) {
        return super.selectObjectById(id);
    }

    public MembersModel selectMemberByName(String name) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE name like ?";
        name = "%" + name + "%";
        return selectObject(sql, name);
    }

    public List<MembersModel> selectAllMembers(Integer start){
        return super.selectListByPage(start,null);
    }

    public void insertMember(MembersModel Member){
        super.saveOrUpdate(Member);
    }
}

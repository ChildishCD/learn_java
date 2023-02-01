package com.javasm.user.dao;

import com.javasm.base.BaseDAO;
import com.javasm.user.model.SmMenuModel;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuDAO extends BaseDAO<SmMenuModel> {

    //根据角色id 查询 菜单列表
    public List<SmMenuModel> selectMenuListByRid(@NonNull Integer rid){
        String sql = "SELECT menu.* FROM sm_menu menu,sm_rel_role_menu rel " +
                "WHERE menu.mid=rel.menu_id AND rel.role_id=?";
        return selectList(sql,rid);
    }
    //根据1级菜单id集合 查询2级菜单
    public List<SmMenuModel> selectMenuListByParentMenuIds(@NonNull List<Integer> midList){
        String mids = midList.stream().map(Objects::toString).collect(Collectors.joining(","));
        String sql = "SELECT * FROM sm_menu WHERE pid IN ( "+mids+" )";
        return selectList(sql);
    }
}

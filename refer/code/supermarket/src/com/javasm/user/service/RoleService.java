package com.javasm.user.service;

import com.javasm.user.dao.MenuDAO;
import com.javasm.user.dao.RoleDAO;
import com.javasm.user.model.SmMenuModel;
import com.javasm.user.model.SmRoleModel;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleService {
    private RoleDAO roleDAO = new RoleDAO();
    private MenuDAO menuDAO = new MenuDAO();
    //根据uid 查询角色信息
    public SmRoleModel queryRoleByRid(Integer rid){
        //通过角色id  查询到 角色信息
        SmRoleModel roleModel = roleDAO.selectObjectById(rid);
        //查询角色下的 菜单信息
        //1级菜单
        List<SmMenuModel> menuList = menuDAO.selectMenuListByRid(rid);
        if (menuList != null){//查询2级菜单
            //SmMenuModel的mid 组成集合
            List<Integer> parentList = menuList.stream().map(SmMenuModel::getMid).collect(Collectors.toList());
            //查询出 mid 的所有子菜单集合
            List<SmMenuModel> childList = menuDAO.selectMenuListByParentMenuIds(parentList);
            //子菜单集合转为map，方便取值Map<父ID, 相同父ID的值集合>
            Map<Integer, List<SmMenuModel>> childMap = childList.stream().collect(Collectors.groupingBy(SmMenuModel::getPid));
            for (SmMenuModel menuModel : menuList){
                List<SmMenuModel> childs = childMap.get(menuModel.getMid());
                menuModel.setChildList(childs);
            }
            roleModel.setMenuList(menuList);
        }
        return roleModel;
    }
}

package com.javasm.user.service;

import com.javasm.user.dao.UserDAO;
import com.javasm.user.model.SmRoleModel;
import com.javasm.user.model.SmUserModel;
import com.javasm.utils.ParameterUtil;

import java.util.List;

public class LoginService {

    private UserDAO userDAO = new UserDAO();
    private RoleService roleService = new RoleService();

    //根据用户名 密码查询用户信息
    public SmUserModel checkLogin(String username, String password) {
        if (!ParameterUtil.check(username, password)) {
            return null;
        }
        SmUserModel userModel = new SmUserModel(username, password);
        List<SmUserModel> smUserModels = userDAO.selectListWhere(userModel);
        if (smUserModels == null ||
                smUserModels.size() < 1 ||
                smUserModels.get(0) == null ||
                smUserModels.get(0).getUid() == null) {
            return null;
        }
        //通过用户名密码 查询到的用户信息
        userModel = smUserModels.get(0);
        //查询 角色信息
        SmRoleModel smRoleModel = roleService.queryRoleByRid(userModel.getRoleId());
        if (smRoleModel != null) {
            userModel.setRole(smRoleModel);
        }
        return userModel;
    }
}

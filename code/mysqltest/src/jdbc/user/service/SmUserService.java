package jdbc.user.service;

import com.mysql.cj.util.StringUtils;
import jdbc.user.model.SmUser;
import jdbc.user.model.SmUserInfo;
import jdbc.user.dao.SmUserDAO;
import jdbc.user.dao.SmUserInfoDAO;

import java.util.List;

public class SmUserService {
    private SmUserDAO smUserDAO ;
    private SmUserInfoDAO smUserInfoDAO;

    public SmUserService() {
        this.smUserDAO = new SmUserDAO();
        this.smUserInfoDAO = new SmUserInfoDAO();
    }
    //根据姓名模糊查询
    public List<SmUser> searchByName(String name){
        if (StringUtils.isNullOrEmpty(name)){
            return null;
        }
        return smUserDAO.selectUserByNameLikely(name);
    }
    //查询用户信息和用户详情
    public SmUser Login(String username, String password){
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(password)){
            return null;
        }
        //获取用户
        SmUser smUser = smUserDAO.selectUserByLoginInfo(username,password);
        if(smUser != null){
            //获取用户信息
            SmUserInfo smUserInfo = smUserInfoDAO.selectSmUserByUid(smUser.getUid().intValue());
            smUser.setSmUserInfo(smUserInfo);
            //更新登录时间
            smUserDAO.updateLoginTime(smUser.getUid().intValue());

        }
        return smUser;
    }
}

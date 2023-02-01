package com.javasm.member.service;

import com.javasm.member.dao.MemberDAO;
import com.javasm.member.model.SmMemberModel;
import com.javasm.utils.ParameterUtil;

import java.time.LocalDateTime;
import java.util.List;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();


    public SmMemberModel queryMemberById(Integer id){
        return memberDAO.selectObjectById(id);
    }

    public List<SmMemberModel> queryMemberList(){
        //假定会员人数无限多，所以查询要加分页，不能一次都查出来
        //也不能把查询结果放到全局变量里
        return memberDAO.selectListByPage();
    }
    public void edit(Integer id,String name,String password,String contact){
        SmMemberModel member = new SmMemberModel();
        if (id != null){
            member = queryMemberById(id);
            member.setUpdateTime(LocalDateTime.now());
        }else {
            member.setCreateTime(LocalDateTime.now());
        }
        member.setMemberName(name);
        member.setPassword(password);
        member.setContact(contact);
        memberDAO.saveOrUpdate(member);
    }
    public SmMemberModel pay(Integer id,Long money){
        if (!ParameterUtil.check(id,money)){
            return null;
        }
        //对金钱正负不做校验，花钱和充钱调同一个方法
        memberDAO.pay(id,money);
        //查询会员最新信息
        return queryMemberById(id);
    }
    public void deleteById(Integer id){
        memberDAO.deleteById(id);
    }
}

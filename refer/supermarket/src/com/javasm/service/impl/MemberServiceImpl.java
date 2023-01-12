package com.javasm.service.impl;

import com.javasm.bean.Member;
import com.javasm.common.ServerResponse;
import com.javasm.dao.MemberDao;
import com.javasm.dao.impl.MemberDaoImpl;
import com.javasm.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Lisa
 * @className: MemberServiceImpl
 * @description:
 * @date: 2021/6/15 16:04
 * @version: 0.1
 * @since: 1.8
 */
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = new MemberDaoImpl();


    @Override
    public ServerResponse<Member> memberLogin(int cardId, String pass) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("id", cardId);
        map.put("pass", pass);
        try {
            List<Member> memberList = memberDao.selectByPrams(map);
            if (!memberList.isEmpty()) {
                return ServerResponse.success(memberList.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.error();
    }

    @Override
    public ServerResponse updateMember(Member member) {

        try {
            if (memberDao.updateMember(member) < 0) {
                return ServerResponse.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.success("充值成功");
    }
}

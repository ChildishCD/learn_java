package com.javasm.service;

import com.javasm.bean.Member;
import com.javasm.common.ServerResponse;

/**
 * @author: Lisa
 * @className: MemberService
 * @description:
 * @date: 2021/6/15 16:04
 * @version: 0.1
 * @since: 1.8
 */
public interface MemberService {

    ServerResponse<Member> memberLogin(int cardId,String pass);

    ServerResponse updateMember(Member member);
}

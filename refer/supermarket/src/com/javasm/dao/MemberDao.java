package com.javasm.dao;

import com.javasm.bean.Member;

/**
 * @author: Lisa
 * @className: MemberDao
 * @description:
 * @date: 2021/6/15 16:05
 * @version: 0.1
 * @since: 1.8
 */
public interface MemberDao extends BaseDao<Member> {
    int updateMember(Member member) throws Exception;
}

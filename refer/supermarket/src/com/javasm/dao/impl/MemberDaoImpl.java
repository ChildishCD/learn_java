package com.javasm.dao.impl;

import com.javasm.bean.Member;
import com.javasm.dao.MemberDao;
import com.javasm.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

/**
 * @author: Lisa
 * @className: MemberDaoImpl
 * @description:
 * @date: 2021/6/15 16:05
 * @version: 0.1
 * @since: 1.8
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {
    private Connection connection;

    public MemberDaoImpl() {
        super(Member.class);
    }

    public MemberDaoImpl(Connection connection) {
        super(Member.class);
        this.connection = connection;
    }

    //不仅适合充值  扣钱
    @Override
    public int updateMember(Member member) throws Exception {
        String sql = "update member set name=?, pass=?, image=?, phone=?, point=?, balance=? where id=?";
        if (connection == null) {
            return new QueryRunner(DBUtil.getDataSource()).update(sql,
                    member.getName(),
                    member.getPass(),
                    member.getImage(),
                    member.getPhone(),
                    member.getPoint(),
                    member.getBalance(),
                    member.getId());
        }
        return new QueryRunner().update(connection, sql,
                member.getName(),
                member.getPass(),
                member.getImage(),
                member.getPhone(),
                member.getPoint(),
                member.getBalance(),
                member.getId());

    }
}

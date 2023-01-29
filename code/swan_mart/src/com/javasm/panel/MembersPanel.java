package com.javasm.panel;

import com.javasm.base.BasePanel;
import com.javasm.service.MembersService;

public class MembersPanel extends BasePanel {
    public MembersPanel(BasePanel prePanel) {
        super(prePanel, new MembersService());
    }

    public void selectMember() {
        listPanel("查询会员", this, new String[][]{
                {"id查询", "idSelect"},
                {"会员名查询", "nameSelect"},
                {"查询全部会员", "totalSelect"}
        });
    }

    public void idSelect() {
        sqlPanel("id名查询", "selectMemberById", new String[]{
                "会员id"
        });
    }

    public void nameSelect() {
        sqlPanel("会员名查询", "selectMemberByName", new String[]{
                "会员名称"
        });
    }

    public void totalSelect() {
        sqlPanel("查询全部会员", "selectAllMembers", false, true, new String[]{

        });
    }

    public void insertMember() {
        sqlPanel("增加会员", "insertMember", new String[]{
                "会员名", "密码", "联系方式"
        });
    }

    public void updateMember() {
        sqlPanel("修改会员", "updateMember", true, false, new String[]{
                "会员名", "密码", "联系方式"
        });
    }

    public void deleteMember() {
        deletePanel();
    }

    public void chargingMember() {
        listPanel("会员充值", this, new String[][]{
                {"余额充值", "addBalance"},
                {"积分充值", "addPoints"}
        });
    }

    public void addBalance() {
        sqlPanel("余额充值", "addBalance", true, false, new String[]{
                "新增的余额"
        });
    }

    public void addPoints() {
        sqlPanel("积分充值", "addPoints", true, false, new String[]{
                "新增的积分"
        });
    }
}

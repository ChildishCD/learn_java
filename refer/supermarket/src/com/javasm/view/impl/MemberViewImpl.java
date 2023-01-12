package com.javasm.view.impl;

import com.javasm.util.InputUtil;
import com.javasm.view.MemberView;

/**
 * @author: Lisa
 * @className: MemberViewImpl
 * @description:
 * @date: 2021/6/15 11:49
 * @version: 0.1
 * @since: 1.8
 */
public class MemberViewImpl implements MemberView {
    @Override
    public void menu() {
        while (true) {
            System.out.println("1. 新增会员");
            System.out.println("2. 删除会员");
            System.out.println("3. 修改会员");
            System.out.println("4. 删除会员");
            System.out.println("5. 会员充值");
            System.out.println("6. 退出");

            int choice = InputUtil.inputInt("^[1-6]{1}$", "请录入1-6的数字:");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    select();
                    break;
                case 5:
                    depositMoney();
                    break;
                case 6:
                    System.out.println("退出会员系统");
                    return;
            }
        }
    }

    @Override
    public void add() {

        
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void select() {

    }

    @Override
    public void depositMoney() {

    }
}

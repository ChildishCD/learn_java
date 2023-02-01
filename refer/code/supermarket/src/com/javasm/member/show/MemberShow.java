package com.javasm.member.show;

import com.javasm.App;
import com.javasm.member.model.SmMemberModel;
import com.javasm.member.service.MemberService;
import com.javasm.user.model.SmMenuModel;
import com.javasm.utils.DateUtilJavasm;

import java.util.List;

public class MemberShow {

    private final static MemberService memberService = new MemberService();

    public static void start(List<SmMenuModel> childList) {
        String goon = "n";
        do {
            //打印二级菜单
            childList.forEach(child -> System.out.println("\t"+child.getMid()+":"+child.getName()));

            System.out.println("请选择操作：");
            int num = App.scanner.nextInt();
            switch (num){
                case 1201:
                    add();
                    break;
                case 1202:
                    update();
                    break;
                case 1203:
                    query();
                    break;
                case 1204:
                    del();
                    break;
                case 1205:
                    pay();
                    break;
                default:
                    System.out.println("菜单编号错误");
                    break;
            }
            System.out.println("是否返回主菜单？y/n");
            goon = App.scanner.next();
        }while (goon.equals("n"));
    }

    private static void pay() {
        viewMemberList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入充值的会员id");
            int id = App.scanner.nextInt();
            System.out.println("请输入充值金额");
            long money = App.scanner.nextLong();
            SmMemberModel pay = memberService.pay(id, money);
            if (pay == null){
                System.out.println("用户不存在");
            }else {
                System.out.println("充值成功,当前余额为："+pay.getBalance());
            }
            System.out.println("是否继续充值？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    private static void del() {
        viewMemberList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入删除的会员id");
            int id = App.scanner.nextInt();
            SmMemberModel member = memberService.queryMemberById(id);
            if (member==null ){
                System.out.println("您输入的会员id错误");
            }else if (member.getBalance() > 0){
                System.out.println("您目前账户余额为："+member.getBalance());
                System.out.println("删除后余额清空，请问是否继续删除？y/n");
                String isdel = App.scanner.next();
                if ("y".equals(isdel)){
                    memberService.deleteById(id);
                    System.out.println("删除成功");
                }
            }
            System.out.println("是否继续删除其他会员？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    private static void query() {
        viewMemberList();
        System.out.println("请输入查看的会员id");
        int id = App.scanner.nextInt();
        SmMemberModel member = memberService.queryMemberById(id);
        if (member != null){
            System.out.println("\t|会员ID|会员号|密码|联系方式|积分|余额|创建时间|修改时间|");
            System.out.printf("\t|%d|%s|%s|%s|%d|%d|%s|%s|\n",
                    member.getId(),
                    member.getMemberName(),
                    member.getPassword(),
                    member.getContact(),
                    member.getIntegral(),
                    member.getBalance(),
                    DateUtilJavasm.timeToStringAll(member.getCreateTime()),
                    DateUtilJavasm.timeToStringAll(member.getUpdateTime())
            );
        }else {
            System.out.println("暂无数据");
        }
    }

    private static void update() {
        viewMemberList();
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请选择要修改的会员id");
            int id = App.scanner.nextInt();
            edit(id);
            System.out.println("修改成功,是否继续修改？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }


    private static void add() {
        String goon = "y";
        while (goon.equals("y")){
            System.out.println("请输入添加的会员信息");
            edit(null);
            System.out.println("添加成功,是否继续添加？y/n");
            //是否继续操作
            goon = App.scanner.next();
        }
    }

    private static void edit(Integer id) {
        //添加和修改都不能 改变 积分和余额
        System.out.println("请输入会员号");
        String name = App.scanner.next();
        System.out.println("请输入密码");
        String pass = App.scanner.next();
        System.out.println("请输入联系方式");
        String contact = App.scanner.next();
        memberService.edit(id,name,pass,contact);
    }

    private static void viewMemberList() {
        List<SmMemberModel> list = memberService.queryMemberList();
        if (list == null){
            System.out.println("暂无数据");
        }else {
            System.out.println("\t|会员ID|会员号|密码|联系方式|积分|余额|");
            list.forEach(member ->{
                System.out.printf("\t|%d|%s|%s|%s|%d|%d|\n",
                        member.getId(),
                        member.getMemberName(),
                        member.getPassword(),
                        member.getContact(),
                        member.getIntegral(),
                        member.getBalance()
                );
            });
        }

    }
}

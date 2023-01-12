package com.javasm.view.impl;

import com.javasm.bean.Goods;
import com.javasm.bean.Member;
import com.javasm.common.CodeEnum;
import com.javasm.common.ShoppingCar;
import com.javasm.service.GoodsService;
import com.javasm.service.MemberService;
import com.javasm.service.OrderInfoService;
import com.javasm.service.impl.GoodsServiceImpl;
import com.javasm.service.impl.MemberServiceImpl;
import com.javasm.service.impl.OrderInfoServiceImpl;
import com.javasm.util.InputUtil;
import com.javasm.view.BuyView;
import com.javasm.vo.GoodVO;

import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: BuyViewImpl
 * @description:
 * @date: 2021/6/15 11:52
 * @version: 0.1
 * @since: 1.8
 */
public class BuyViewImpl implements BuyView {
    @Override
    public void menu() {
        while (true) {
            System.out.println("1.购买商品");
            System.out.println("2.修改商品数量");
            System.out.println("3.删除购物车中的商品");
            System.out.println("4.展示购物车商品列表");
            System.out.println("5.浏览过的商品");
            System.out.println("6.支付");
            System.out.println("7.退出");

            int choice = InputUtil.inputInt("^[1-7]{1}$", "请录入1-7的数字:");
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
                    seeGoods();
                    break;
                case 6:
                    pay();
                    break;
                case 7:
                    System.out.println("退出购买系统");
                    return;
            }
        }
    }

    private static GoodsService goodsService;

    static {
        goodsService = new GoodsServiceImpl();
    }

    @Override
    public void add() {
        //购买流程开始
        System.out.println("展示当前在售的商品:");
        new GoodViewImpl().select();
        System.out.println("请录入要购买的商品的id:");
        int goodId = InputUtil.inputInt();
        System.out.println("当前购买的商品的详情如下:");
        Goods buyGood = goodsService.selectGoodsById(goodId).getData();
        System.out.println(buyGood);
        //查看了一个产品 就需要将这个商品存储 浏览过的容器里面

        System.out.println("确认购买吗? y/n");
        if ("n".equals(InputUtil.input.next())) {
            return;
        }
        GoodVO goodVO = ShoppingCar.car.get(goodId);
        int lastBuy = 0;
        if (goodVO != null) {
            lastBuy = goodVO.getBuyNum();
        }
        int buyNum;
        do {
            System.out.println("录入要购买的商品的数量:");
            buyNum = InputUtil.inputInt();
            if ((buyNum + lastBuy) > buyGood.getStore()) {
                System.out.println("超出了库存，无法购买的! 请重新录入");
            } else break;
        } while (true);

        System.out.println("成功购买了" + buyNum + "个," + buyGood.getGoodName() + "成功了!");
        //将购买的商品存储购物车----> 存储购买的多个商品---> 容器 集合或者数组--->list map set
        //购物项--->购物车里面的商品的信息  商品+购买量+小计  VO  CartItem

        ShoppingCar.put(buyNum, buyGood);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {
        if (!ShoppingCar.select()) {
            return;
        }
        System.out.println("要修改的商品的id：");
        int gid = InputUtil.inputInt();
        GoodVO goodVO = ShoppingCar.car.get(gid);
        Integer store = goodVO.getGoods().getStore();
        int buyNum;
        do {
            System.out.println("录入要购买的商品的数量:");
            buyNum = InputUtil.inputInt();
            if (buyNum > store) {
                System.out.println("超出了库存，无法购买的! 请重新录入");
            } else break;
        } while (true);
        goodVO.setBuyNum(buyNum);
        ShoppingCar.car.put(gid, goodVO);
    }

    @Override
    public void select() {
        ShoppingCar.select();
    }

    @Override
    public void seeGoods() {

    }

    @Override
    public void pay() {
        BigDecimal totalMoney = ShoppingCar.getTotalMoney();
        System.out.println("一共需要支付:" + totalMoney);
        System.out.println("请选择支付方式:");
        System.out.println(CodeEnum.CASH_PAY.getCode() + ":" + CodeEnum.CASH_PAY.getMsg());
        System.out.println(CodeEnum.CARD_PAY.getCode() + ":" + CodeEnum.CARD_PAY.getMsg());
        int payType = InputUtil.inputInt();
        Member member = new Member();
        member.setId(CodeEnum.VISIT.getCode());

        if (CodeEnum.CARD_PAY.getCode().equals(payType)) {
            //录入会员信息
            System.out.println("录入会员卡号:");
            int cardNo = InputUtil.inputInt();
            System.out.println("录入会员卡密码:");
            String pass = InputUtil.input.next();

            MemberService memberService = new MemberServiceImpl();
            member = memberService.memberLogin(cardNo, pass).getData();
            BigDecimal balance = member.getBalance();
            if (balance.compareTo(totalMoney) < 0) {
                System.out.println("余额不足，请充值:");
                BigDecimal money = InputUtil.input.nextBigDecimal();
                member.setBalance(balance.add(money));
                member.setPoint(member.getPoint() + money.floatValue());
                System.out.println(memberService.updateMember(member));
            }
        }

        //提交支付
        OrderInfoService orderInfoService = new OrderInfoServiceImpl();
        System.out.println(orderInfoService.pay(totalMoney, member, payType));
        ShoppingCar.car.clear();
    }
}

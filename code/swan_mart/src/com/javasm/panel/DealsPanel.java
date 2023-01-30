package com.javasm.panel;

import com.javasm.banner.AdBanner;
import com.javasm.base.BasePanel;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.ProductsModel;
import com.javasm.bean.Shopping;
import com.javasm.service.DealsService;
import com.javasm.service.MembersService;
import com.javasm.service.ProductsService;

import java.util.Arrays;
import java.util.Map;

public class DealsPanel extends BasePanel<MemberOrdersModel> {
    //    private DealsService service = new DealsService();
    //TODO:商品减少
    public DealsPanel(BasePanel prePanel) {
        super(prePanel, new DealsService());
    }

    public DealsService getService(){
        return (DealsService)service;
    }

    public void shopping() {
        listPanel("购买商品", this, new String[][]{
                {"添加商品", "addP2Car"},
                {"修改商品数量", "addPNum2Car"},
                {"删除商品", "dropP2Car"},
                {"查看购物车", "showCar"},
                {"支付", "payCar"}
        });
    }

    public void payCar() {
        if (getService().shoppingCar == null || getService().shoppingCar.isEmpty()) {
            System.out.println("您还未向购物车内添加商品！");
            return;
        }
        navbar.push("支付");
        AdBanner.printSeparator();
        printNaviBanner();
        System.out.println("购物车内的商品有");
        getService().shoppingCar.values().forEach(System.out::println);
        System.out.println("请问有会员卡吗?(游客请输入 1 )");
        MembersService mService = new MembersService();
        //设置游客
        try {
            mService.selectMemberById(Arrays.asList("1"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Integer mId = 1;
        Double mBalance = 0.0;
        while (checkGo(go)) {
            if (idCheckPanel(mService)) {
                mId = mService.results.get(0).getId();
                mBalance = mService.results.get(0).getBalance();
                System.out.println("欢迎会员: " + mService.results.get(0).getName());
                break;
            } else {
                System.out.println("会员不存在！");
                System.out.println("您希望继续输入会员id吗: (Y/N)");
                go = scanner.next();
            }
        }
        initPanel();
        Double price = getService().getTotalPrice();
        while (checkGo(go)) {
            System.out.println("1--余额支付");
            System.out.println("2--现金支付");
            System.out.println("您好,请支付模式：(输入其他字符退出)");
            String flag = scanner.next();
            if (flag.equals("1")) {
                //如果游客点击
                if (mId == 1) {
                    System.out.println("尊敬的游客,请您使用现金支付！");
                    continue;
                }
                //查看余额是否充足
                if (mBalance < price) {
                    System.out.println("会员余额不足，请您使用现金支付！");
                    continue;
                }
                //余额支付
                try {
                    getService().insertOrder(mService);
                    System.out.println("余额支付成功！");
                    getService().shoppingCar.clear();
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("余额支付失败！");
                }
                //
                break;
            } else if (flag.equals("2")) {
                try {
                    getService().insertOrder(mService);
                    System.out.println("现金支付成功！");
                    getService().shoppingCar.clear();
                } catch (Exception e) {
                    System.out.println("现金支付失败！");
                }
                break;
            } else {
                System.out.println("检测到存在字符,您希望继续吗: (Y/N)");
                go = scanner.next();
            }
        }
        navbar.pop();
        initPanel();
    }

    public void showCar() {
        navbar.push("查看购物车");
        AdBanner.printSeparator();
        printNaviBanner();
        getService().shoppingCar.values().forEach(System.out::println);
        navbar.pop();
    }

    public void dropP2Car() {
        navbar.push("删除商品");
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();

            System.out.println("请输入商品id:");
            String idStr = scanner.next();
            if (idStr.matches("^[0-9]+$") && getService().shoppingCar.containsKey(Integer.valueOf(idStr))) {
                var id = Integer.valueOf(idStr);
                System.out.println("您希望删除以下商品吗：(Y/N)");
                Shopping shopping = getService().shoppingCar.get(id);
                System.out.println(shopping);
                if (checkGo(scanner.next())) {
                    getService().shoppingCar.remove(id);
                    System.out.println("删除成功！");
                } else {
                    System.out.println("未删除本商品！");
                }
            } else {
                System.out.println("无结果！");
            }

            System.out.println("您希望继续删除商品吗: (Y/N)");
            go = scanner.next();
        }
        navbar.pop();
        initPanel();
    }

    public void addPNum2Car() {
        navbar.push("修改商品数量");
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();

            System.out.println("请输入商品id:");
            String idStr = scanner.next();
            if (idStr.matches("^[0-9]+$") && getService().shoppingCar.containsKey(Integer.valueOf(idStr))) {
                var id = Integer.valueOf(idStr);
                System.out.println("目前数量如下：");
                Shopping shopping = getService().shoppingCar.get(id);
                System.out.println(shopping);
                while (true) {
                    System.out.println("请输入商品数量：(输入 0 删除商品)");
                    String num = scanner.next();
                    if (num.matches("^[0-9]+$")) {
                        var n = Integer.parseInt(num);
                        if (n > 0 && n < shopping.getProduct().getInventory()) {
                            shopping.setCount(n);
                            getService().shoppingCar.put(id, shopping);
                            System.out.println("添加成功！");
                            break;
                        } else if (n == 0) {
                            getService().shoppingCar.remove(id);
                            System.out.println("已删除！");
                            break;
                        } else {
                            System.out.println("库存不足！");
                        }
                    } else {
                        System.out.println("请输入正确的商品数量！");
                    }
                }
            } else {
                System.out.println("无结果！");
            }

            System.out.println("您希望继续修改商品数量吗: (Y/N)");
            go = scanner.next();
        }
        navbar.pop();
        initPanel();
    }

    public void addP2Car() {
        navbar.push("添加商品");
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();
            ProductsService productsService = new ProductsService();
            if (idCheckPanel(productsService)) {
                ProductsModel product = productsService.results.get(0);
                while (true) {
                    System.out.println("请输入商品数量：(输入 0 取消)");
                    String num = scanner.next();
                    if (num.matches("^[0-9]+$")) {
                        var n = Integer.parseInt(num);
                        if (n > 0 && n < product.getInventory()) {
                            getService().shoppingCar.put(product.getId(), new Shopping(n, product));
                            clearPanel();
                            System.out.println("添加成功！");
                            break;
                        } else if (n == 0) {
                            System.out.println("已取消！");
                            break;
                        } else {
                            System.out.println("库存不足！");
                        }
                    } else {
                        System.out.println("请输入正确的商品数量！");
                    }
                }
            }
            System.out.println("您希望继续添加商品吗: (Y/N)");
            go = scanner.next();
        }
        navbar.pop();
        initPanel();
    }

    public void ordersCheck() {
        listPanel("订单查询", this, new String[][]{
                {"订单编号查询", "idSelect"},
                {"商品编号查询", "pIdSelect"},
                {"会员编号查询", "mIdSelect"}
        });
    }

    public void idSelect() {
        sqlPanel("订单编号查询", "selectOrderById", new String[]{
                "订单id"
        });
    }

    public void pIdSelect() {
        sqlPanel("订单编号查询", "selectOrderByPId", new String[]{
                "商品id"
        });
    }

    public void mIdSelect() {
        sqlPanel("订单编号查询", "selectOrderByMId", new String[]{
                "会员id"
        });
    }


    public void productsRanking() {

    }
}

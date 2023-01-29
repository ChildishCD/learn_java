package com.javasm.panel;

import com.javasm.banner.AdBanner;
import com.javasm.base.BasePanel;
import com.javasm.bean.MemberOrdersModel;
import com.javasm.bean.ProductsModel;
import com.javasm.bean.Shopping;
import com.javasm.service.DealsService;
import com.javasm.service.MembersService;
import com.javasm.service.ProductsService;

public class DealsPanel extends BasePanel<MemberOrdersModel> {
    private DealsService service = new DealsService();

    public DealsPanel(BasePanel prePanel) {
        super(prePanel);
    }

    public void shopping() {
        listPanel("购买商品", this, new String[][]{
                {"添加商品", "addP2Car"},
                {"修改商品数量", "addPNum2Car"},
                {"删除商品", "dropP2Car"},
                {"查看购物车", "showCar"},
                {"支付", "payChar"}
        });
    }

    public void payChar() {
        if (service.shoppingCar == null || service.shoppingCar.isEmpty()) {
            System.out.println("您还未向购物车内添加商品！");
            return;
        }
        navbar.push("支付");
        AdBanner.printSeparator();
        printNaviBanner();
        service.shoppingCar.values().forEach(System.out::println);
        navbar.pop();
        System.out.println("请问有会员卡吗?");
        Integer memberId = 1;
        while (checkGo(go)) {
            MembersService mService = new MembersService();
            if (idCheckPanel(mService)) {
                memberId = mService.results.get(0).getId();
                System.out.println("欢迎会员: " + mService.results.get(0).getName());
                break;
            } else {
                System.out.println("会员不存在！");
                System.out.println("您希望继续输入会员id吗: (Y/N)");
                go = scanner.next();
            }
        }
        initPanel();
        while (checkGo(go)) {
            System.out.println("1--余额支付");
            System.out.println("2--现金支付");
            System.out.println("您好,请支付模式：(输入其他字符退出)");
            String flag = scanner.next();
            if (flag.equals("1")) {
                if (memberId == 1) {
                    System.out.println("尊敬的游客,请您使用现金支付！");
                    continue;
                }

                service.shoppingCar.clear();
                break;
            } else if (flag.equals("2")) {

                service.shoppingCar.clear();
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
        service.shoppingCar.values().forEach(System.out::println);
        navbar.pop();
    }

    public void dropP2Car() {
        navbar.push("删除商品");
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();

            System.out.println("请输入商品id:");
            String idStr = scanner.next();
            if (idStr.matches("^[0-9]+$") && service.shoppingCar.containsKey(Integer.valueOf(idStr))) {
                var id = Integer.valueOf(idStr);
                System.out.println("您希望删除以下商品吗：(Y/N)");
                Shopping shopping = service.shoppingCar.get(id);
                System.out.println(shopping);
                if (checkGo(scanner.next())) {
                    service.shoppingCar.remove(id);
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
            if (idStr.matches("^[0-9]+$") && service.shoppingCar.containsKey(Integer.valueOf(idStr))) {
                var id = Integer.valueOf(idStr);
                System.out.println("目前数量如下：");
                Shopping shopping = service.shoppingCar.get(id);
                System.out.println(shopping);
                while (true) {
                    System.out.println("请输入商品数量：(输入 0 删除商品)");
                    String num = scanner.next();
                    if (num.matches("^[0-9]+$")) {
                        var n = Integer.parseInt(num);
                        if (n > 0 && n < shopping.getProduct().getInventory()) {
                            shopping.setCount(n);
                            service.shoppingCar.put(id, shopping);
                            System.out.println("添加成功！");
                            break;
                        } else if (n == 0) {
                            service.shoppingCar.remove(id);
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

    public void add2Car() {
        navbar.push("添加商品");
        while (checkGo(go)) {
            AdBanner.printSeparator();
            printNaviBanner();
            ProductsService productsService = new ProductsService();
            if (idCheckPanel(new ProductsService())) {
                ProductsModel product = productsService.results.get(0);
                while (true) {
                    System.out.println("请输入商品数量：(输入 0 取消)");
                    String num = scanner.next();
                    if (num.matches("^[0-9]+$")) {
                        var n = Integer.parseInt(num);
                        if (n > 0 && n < product.getInventory()) {
                            service.shoppingCar.put(product.getId(), new Shopping(n, product));
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

    }

    public void productsRanking() {

    }
}

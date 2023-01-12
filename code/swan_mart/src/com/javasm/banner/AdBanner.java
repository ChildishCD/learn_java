package com.javasm.banner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdBanner {

    public static void goodbyeBanner() {
        System.out.println("======== (˙<>˙)~ 感 谢 光 临 天 鹅 集 市 ========");
        System.out.println("|           您的选择是我们进步的最大动力           |");
        System.out.println("|                  祝您购物愉快!                |");
        System.out.println("======== (˙<>˙)~ Wish 2 see you again ========");
    }

    public static void welcomeBanner() {
        //显示商店的主要信息
        System.out.println("======== (˙<>˙)/ 欢 迎 来 到 天 鹅 集 市 ========");
        System.out.println("|          开业大酬宾！新春好礼尽享折上折！         |");
        System.out.println("|    仅需200元加入会员,送80元购物津贴！冲多送多！    |");
        System.out.println("======== (˙<>˙)/ Welcome to Swan Mart ========");
    }

    public static void printSeparator(){
        List<String> separator = new ArrayList<>();
        separator.add("会 员 购 物 (˙<>˙)/ 就 选 天 鹅");
        separator.add("所 有 商 品 (˙<>˙)/ 五 折 起 售");
        separator.add("保 障 新 鲜 (˙<>˙)/ 只 卖 一 天");
        separator.add("新 春 佳 节 (˙<>˙)/ 好 礼 相 送");
        separator.add("服 务 周 到 (˙<>˙)/ 宾 至 如 归");
        separator.add("入 会 两 百 (˙<>˙)/ 还 送 八 十");
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt(separator.size());
        System.out.println("-------- "+separator.get(i)+" --------");
    }
}

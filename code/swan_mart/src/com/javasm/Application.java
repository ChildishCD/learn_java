package com.javasm;

import com.javasm.banner.AdBanner;
import com.javasm.panel.MainPanel;

public class Application {
    public static void main(String[] args) {
        //欢迎横幅
        AdBanner.welcomeBanner();
        //启动管理程序，从选择用户登录开始
        MainPanel.getInstance().star();
        //再见横幅
        AdBanner.goodbyeBanner();
    }
}

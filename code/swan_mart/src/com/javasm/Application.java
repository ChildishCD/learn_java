package com.javasm;

import com.javasm.banner.AdBanner;
import com.javasm.panel.MainPanel;

public class Application {
    public static void main(String[] args) {
        //欢迎横幅
        AdBanner.welcomeBanner();
        //开始界面
        MainPanel.getInstance().star();
        //再见横幅
        AdBanner.goodbyeBanner();
    }
}

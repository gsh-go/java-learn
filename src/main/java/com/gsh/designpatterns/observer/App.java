package com.gsh.designpatterns.observer;

/**
 * @Author: gsh
 * @Date: Created in 2018/9/19 16:00
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        WechatServer server = new WechatServer();

        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInformation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInformation("JAVA是世界上最好用的语言！");
    }
}

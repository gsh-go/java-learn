package com.gsh;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @Author: gsh
 * @Date: Created in 2018/10/31 14:59
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for(;;){
                System.out.println("......................");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                  //  e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(5000);
        System.out.println("1111111111111111111111111");
       // thread.interrupt();
        System.out.println("2222222222222222222");

    }
}

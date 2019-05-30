package com.example.core.test;

import java.util.UUID;

/**
 * @Author Ryan
 * @Date 2018/9/18  9:58
 * @desc
 */
public class OtherTestCase {

    /**
     *
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {

        while(true){
            System.out.println("uuid = " + uuid());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
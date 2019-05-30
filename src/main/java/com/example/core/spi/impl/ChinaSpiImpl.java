package com.example.core.spi.impl;

import com.example.core.spi.SpiInterface;

/**
 * @Author wangwei
 * @Date 2019/3/21 16:55
 * -描述-
 */
public class ChinaSpiImpl implements SpiInterface {
    @Override
    public void say() {
        System.out.println("哈喽，美女！");
    }
}

package com.example.core.spi.impl;

import com.example.core.spi.SpiInterface;

/**
 * @Author wangwei
 * @Date 2019/3/21 16:56
 * -描述-
 */
public class EngishSpiImpl implements SpiInterface {
    @Override
    public void say() {
        System.out.println("Hello beauty-girl!");
    }
}

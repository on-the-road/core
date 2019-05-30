package com.example.core.controller;



/**
 * @Author wangwei
 * @Date 2018/10/29 14:15
 * -描述-
 * Springcloud-Stream 集成 rabbitmq
 * @EnableBinding注解实现对消息通道的绑定，
 * 我们在该注解中还传入了一个参数Sink.class，Sink是一个接口，
 * 该接口是Spring Cloud Stream中默认实现的对输入消息通道绑定的定义
 *
 * 这个只是一个简单的消费者部分!!!!!!
 */
//@EnableBinding(Sink.class)
public class SinkReceive {
    /**
     * @StreamListener注解，该注解表示该方法为消息中间件上数据流的事件监听器，
     * Sink.INPUT参数表示这是input消息通道上的监听处理器
     * 不太懂....
     * @param payload
     */
    //@StreamListener(Sink.INPUT)
    public void receive(Object payload){
        System.out.println("receive-payload:" + payload);
    }

}

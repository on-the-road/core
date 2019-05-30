package com.example.core.selfStarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangwei
 * @Date 2019/5/15 11:35
 * -描述-
 * - 创建自动化配置类，这个相当于就是一个普通的Java配置类，可以在这里创建Bean
 * - 并可获得与application.properties属性文件相对应的属性类的Bean
 */

@Configuration //相等于一个普通的java配置类
@ConditionalOnClass({StarterDemo.class}) //当StarterDemo 在类路劲的条件下
//将 application.properties 的相关的属性字段与该类一一对应，并生成 Bean
@EnableConfigurationProperties(PrefixProperties.class)
public class MyselfAutoConfiguration {
    //todo 注入属性类
    @Autowired
    private PrefixProperties prefixProperties;

    @Bean
    @ConditionalOnMissingBean(StarterDemo.class) //当容器没有这个Bean的时候才创建这个Bean
    public StarterDemo starterDemo(){
        StarterDemo starterDemo = new StarterDemo();
        starterDemo.setWords(prefixProperties.getParam());
        return starterDemo;

    }

    /**
     *  -- 接下来要做的就是
     *  在META-INF目录下创建spring.factories，这个属性文件可重要啦
     *  因为SpringBoot自动化配置最终就是要扫描META-INF/spring.factories来加载项目的自动化配置类
     *  内容 --
     *  org.springframework.boot.autoconfigure.EnableAutoConfiguration=
     *  com.example.core.MyselfAutoConfiguration
     *
     *  -- 接下来，打成jar包，可以引入到所需要的项目中
     *  在项目中的配置文件application.properties中可以配置如下：
     *  myself.words=springboot
     *
     *  -- 接下来，在程序中注入 StarterDemo 类
     *  可以调用类中的 myselfStarter方法
     *
     */


}

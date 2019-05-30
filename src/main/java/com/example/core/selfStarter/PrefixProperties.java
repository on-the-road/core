package com.example.core.selfStarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangwei
 * @Date 2019/5/15 11:23
 * -描述-
 * - 创建属性类，prefix = "myself"代表该项目在属性文件中配置的前缀，
 * - 即可以在属性配置文件中通过 myself.words=springboot，就可以改变属性类字段words的值了
 * - 一般情况下会提供默认值
 */
@ConfigurationProperties(prefix = "myself")
public class PrefixProperties {
    public static final String DEFAULT_PARAM = "world";
    private String param = DEFAULT_PARAM;

    public static String getDefaultParam() {
        return DEFAULT_PARAM;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

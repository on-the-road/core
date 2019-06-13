package com.example.core.algorithem;

/**
 * @Author wangwei
 * @Date 2019/6/4 15:14
 * -描述- 枚举的序列化和反序列化是有特殊定制的，不是通过反射
 * 枚举实现的单例，相当简洁
 */
public enum EumeSingleton implements A{
    INSTANCE {
        @Override
        public void doSomething() {
            System.out.println("This is a singleton from Enum!");
        }
    };  //todo 这里的域(field)是EumeSingleton的一个实例

    //todo 这里隐藏了一个私有的无参构造函数
    private EumeSingleton(){}
}
interface A{
    void doSomething();
}

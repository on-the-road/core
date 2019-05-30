package com.example.core.test;

/**
 * @Author wangwei
 * @Date 2018/11/19 18:27
 * -描述- 一道关于 jvm的题，很有意思
 */
public class StaticTest {

    /**
     * 请问这段程序输出什么？
     * Java中赋值顺序：
     * 父类的静态变量赋值
     * 自身的静态变量赋值
     * 父类成员变量赋值和父类块赋值
     * 父类构造函数赋值
     * 自身成员变量赋值和自身块赋值
     * 自身构造函数赋值
     * @param args
     *  -- 1.首先不能说上面的规则不正确，而是不能简单的套用这个规则
     *  -- 2.目前代码的顺序，结果是：2 3 a=110,b=0 1 4
     * todo 实例初始化不一定要在类初始化结束之后才开始初始化
     * todo 类的生命周期是：加载->验证->准备->解析->初始化->使用->卸载
     * todo 只有在准备阶段和初始化阶段才会涉及类变量的初始化和赋值，因此只针对这两个阶段进行分析
     * todo 类的准备阶段需要做的是为类变量分配内存并设置默认值，因此类变量st为null、b为0
     * todo 需要注意的是如果类变量是final，编译时javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据ConstantValue的设置将变量设置为指定的值，
     * todo 如果这里这么定义：static final int b=112,那么在准备阶段b的值就是112，而不再是0了
     * todo 类的初始化阶段需要做的是执行类构造器
     * todo 类构造器是编译器收集所有静态语句块和类变量的赋值语句按语句在源码中的顺序合并生成类构造器，对象的构造方法是()，类的构造方法是()，可以在堆栈信息中看到
     * todo 因此先执行第一条静态变量的赋值语句即st = new StaticTest (),此时会进行对象的初始化
     * todo 对象的初始化是先初始化成员变量再执行构造方法，因此打印2->设置a为110->执行构造方法(打印3,此时a已经赋值为110，但是b只是设置了默认值0，并未完成赋值动作)
     *
     */

    public static void main(String[] args) {
        staticFunction();
    }
    /**
     * 分析：没有构造对象，所以构造方法不执行,只执行静态代码块和静态方法
     *       注释掉此句代码(或是去掉static关键字)，执行结果：1 4
     *       这里代码的顺序也影响结果
     */

    static StaticTest st = new StaticTest();
    //这是一个静态代码块

    static {
        System.out.println("1");
    }
    //这是一个普通代码块
    {
        System.out.println("2");
    }

    //这是一个构造方法
    StaticTest(){
        System.out.println("3");
        System.out.println("a=" +a+ ",b= " + b);
    }
    //这是一个静态方法
    public static void staticFunction() {
        System.out.println("4");
    }

    //这是两个成员变量
    int a = 110;
    static  int b = 112;
}

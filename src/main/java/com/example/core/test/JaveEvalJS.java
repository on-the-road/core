package com.example.core.test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * @Author wangwei
 * @Date 2019/8/1 18:21
 * -描述-
 */
public class JaveEvalJS {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        String jsFileName = "aa.js";   // 读取js文件

        FileReader reader = new FileReader(jsFileName);   // 执行指定脚本
        engine.eval(reader);

        if(engine instanceof Invocable) {
            Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数

// c = merge(2, 3);

            Double c = (Double)invoke.invokeFunction("getTimes", "aa.mov");

            System.out.println("c = " + c);
        }

        reader.close();

    }
}

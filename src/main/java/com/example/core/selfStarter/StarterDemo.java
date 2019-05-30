package com.example.core.selfStarter;

/**
 * @Author wangwei
 * @Date 2019/5/15 11:19
 * -描述-
 */
public class StarterDemo {
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
    public String myselfStarter(String words){
        return "say" + words;
    }
}

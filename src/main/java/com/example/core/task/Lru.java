package com.example.core.task;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  用LinkedHashMap实现LRU缓存淘汰算法
 * @param <K>
 * @param <V>
 */
public class Lru<K,V> {
    private static final float hashLoadFactor = 0.75f;
    private LinkedHashMap<K,V> map;
    private int cacheSize;

    public Lru(int cacheSize){
        this.cacheSize = cacheSize;
        int capacity = (int)Math.ceil(cacheSize/hashLoadFactor) + 1;
        map = new LinkedHashMap<K,V>(capacity,hashLoadFactor,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public synchronized V get(K key){
        return map.get(key);
    }

    public synchronized void put(K key,V value){
        map.put(key,value);
    }

    public synchronized void  clear(){
        map.clear();
    }

    public synchronized int usedSize(){
        return map.size();
    }

    public void print(){
        for(Map.Entry<K,V> entry: map.entrySet()){
            System.out.println(entry.getValue() + "---");
        }
    }

}

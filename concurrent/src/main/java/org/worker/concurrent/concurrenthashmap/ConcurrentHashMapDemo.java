package org.worker.concurrent.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peiru wang
 * @date 2021/6/16
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("name", "feichen");
        System.out.println(map.get("name"));
    }
}

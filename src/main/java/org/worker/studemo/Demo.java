package org.worker.studemo;

import net.sf.cglib.proxy.Enhancer;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(11);
        map.put("name", "feichen");
        System.out.println(map.get("name"));

        LRUCache lruCache = new LRUCache(2);
        lruCache.get(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3, 3);

        System.out.println("Hello World");

        ITeaching instance = (ITeaching) new TeachingProxy(new TeachingImpl()).getInstance();

        instance.giveALesson();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TeachingImpl.class);
        enhancer.setCallback(new TeachingProxy2());

        TeachingImpl teachingImpl = (TeachingImpl) enhancer.create();

        teachingImpl.giveALesson();
    }
}

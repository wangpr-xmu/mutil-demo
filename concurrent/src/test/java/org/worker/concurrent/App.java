package org.worker.concurrent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            entry.getValue();
            entry.getKey();
//            Arrays.copyOf(new int[]{1,3}, 2)
        }
    }
}

package com.hit.edu.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: Li dong
 * @date: 2023/12/2 21:42
 * @description:
 */
public class MapController {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 1);
        map.put("c", 1);
        for (int i = 0; i < 13; i++) {
            map.put(i + "", i);
        }
        System.out.println(map);

        ConcurrentMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.putIfAbsent("a", 1);

        int a = 10 << 1;
        System.out.println(a);

    }
}

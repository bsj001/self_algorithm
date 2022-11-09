package com.bsj;

import org.junit.Test;

import java.util.*;

/**
 * 
 * Java HashMap按照value值排序的方法
 * 
 * HashMap的value值没有排序功能，若要进行较轻松的排序，可改写Comparator接口方法compare进行排序，代码如下： 
 */
public class HashMapSort {
    
    @Test
    public void sort(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("d", 2);
        map.put("c", 1);
        map.put("b", 1);
        map.put("a", 3);
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        //排序前
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id);
        }
        //根据value排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
                //return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id);
        }
    }
}



package com.example.suanfa.redis;


import java.util.LinkedHashMap;
import java.util.Map;

/**
     * Title: 使用LinkedHashMap实现LRU算法
     * Description:
     * @author rico
     * @created 2017年5月12日 上午11:32:10
     */
public class LinkedHashMapLRU<K,V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private static final long serialVersionUID = 1L;

    public LinkedHashMapLRU(int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * @description 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
     *              删除最不经常使用的元素
     * @author rico
     * @created 2017年5月12日 上午11:32:51
     * @param eldest
     * @return
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        if(size() > 6){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        LinkedHashMapLRU<Character, Integer> lru = new LinkedHashMapLRU<Character, Integer>(
                16, 0.75f, true);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}

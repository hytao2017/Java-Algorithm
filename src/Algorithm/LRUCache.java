package Algorithm;

import java.util.*;

class LRUCache {

    HashMap<Integer,Integer> map = new HashMap<>();
    Deque<Integer> queue = new ArrayDeque<>();
    int limit = 0;

    public LRUCache(int capacity) {
        limit = capacity;
    }
    
    public int get(int key) {
        queue.remove(key);
        queue.add(key);
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            queue.remove(key);
            queue.add(key);
            map.put(key,value);
            return;
        }


        if (map.size() >= limit){
            map.remove(queue.pollFirst());
        }else {
            queue.add(key);
        }
        map.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
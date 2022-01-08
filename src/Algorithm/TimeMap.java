package Algorithm;

import java.util.*;

class TimeMap {

    private HashMap<Integer,DataObject> map;
    private TreeSet<Integer> list;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
        list = new TreeSet<>();
    }
    
    public void set(String key, String value, int timestamp) {
        DataObject obj = new DataObject(key,value);
        map.put(timestamp,obj);
        list.add(timestamp);
    }
    
    public String get(String key, int timestamp) {
        if (map.containsKey(timestamp)){
            return map.get(timestamp).key.equals(key) ? map.get(timestamp).value : "";
        }else {
            while (list.floor(timestamp) != null){
                Integer index = list.floor(timestamp);
                DataObject data = map.get(index);
                if (data.key.equals(key)){
                    return data.value;
                }else {
                    timestamp--;
                }
            }
            return "";
        }
    }



    class DataObject{
        private String key;
        private String value;

        public DataObject(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
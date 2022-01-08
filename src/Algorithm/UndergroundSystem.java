package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class UndergroundSystem {

    private HashMap<String, List<Integer>> dataMap;
    private HashMap<Integer, UserData> tempMap;

    public UndergroundSystem() {
        this.dataMap = new HashMap<>();
        this.tempMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        if (!tempMap.containsKey(id)){
            UserData data = new UserData(stationName, t);
            tempMap.put(id,data);
        }
    }
    
    public void checkOut(int id, String stationName, int t) {
        if (tempMap.containsKey(id)){
            UserData data = tempMap.get(id);
            String index = data.stationName + '-' + stationName;
            int time = t - data.t;
            if (!dataMap.containsKey(index)){
                List<Integer> list = new ArrayList<>();
                list.add(time);
                dataMap.put(index,list);
            }else {
                List<Integer> list = dataMap.get(index);
                list.add(time);
                dataMap.put(index,list);
            }
            tempMap.remove(id);
        }
    }
    
    public double getAverageTime(String startStation, String endStation) {
        List<Integer> data = dataMap.get(startStation + '-' + endStation);
        return data.stream().mapToInt(Integer::intValue).average().orElse(0D);
    }

    class UserData{
        String stationName;
        int t;

        public UserData(String stationName, int t) {
            this.stationName = stationName;
            this.t = t;
        }

    }
}
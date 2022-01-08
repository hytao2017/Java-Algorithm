package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MedianFinder {

    private List<Integer> preList;
    private List<Integer> nowList;
    private double preTotal;
    private double nowTotal;
    private double result;

    /** initialize your data structure here. */
    public MedianFinder() {
        preList = new ArrayList<>();
        nowList = new ArrayList<>();
        preTotal = 0;
        nowTotal = 0;
        result = 0;
    }
    
    public void addNum(int num) {
        nowList.add(num);
        nowTotal = nowTotal + num;
    }
    
    public double findMedian() {
        int n = preList.size();
        preList.addAll(nowList);

        int length = n + nowList.size();

        Collections.sort(preList);

        if (length % 2 == 0){
            int i = length / 2;
            result = ((double)preList.get(i - 1) + (double)preList.get(i)) / 2;
        }else {
            result = preList.get(length / 2);
        }

        nowTotal = 0;
        nowList = new ArrayList<>();

        return result;
    }
}
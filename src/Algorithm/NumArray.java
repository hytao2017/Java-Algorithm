package Algorithm;

import java.util.Arrays;

class NumArray {

    private int[] arr;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.arr = new int[nums.length];

        int sum = 0;
        for (int i = 0; i <nums.length ; i++) {
            sum = sum + nums[i];
            arr[i] = sum;
        }

    }
    
    public int sumRange(int left, int right) {

        return arr[right] - arr[left] + nums[left];
    }

}
package com.siyuan.leetcode;


public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        int[] list = new int[2];
        if (nums.length > 0){
            for (int i=0;i < nums.length;i++){
                for (int j = i; j < nums.length;j++){
                    if (nums[i] + nums[j] == target){
                        list[0] = i;
                        list[1] = j;
                    }
                }
            }
        }else{
            return  null;
        }

        return list;
    }
}

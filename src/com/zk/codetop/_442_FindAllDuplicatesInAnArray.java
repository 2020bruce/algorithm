package com.zk.codetop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _442_FindAllDuplicatesInAnArray {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates(nums));
    }
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            while(nums[i] != nums[nums[i] - 1]){
                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            }
        }
        for(int i = 0; i < n; i++){
            if(i != nums[i] - 1){
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}
























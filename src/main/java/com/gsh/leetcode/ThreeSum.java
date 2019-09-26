package com.gsh.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: gsh
 * @Date: Created in 2019/8/22 8:02
 * @Description:
 */
public class ThreeSum {

    public static void main(String[] args) {
        // int[] nums = [-1, 0, 1, 2, -1, -4];
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        Solution solution = new Solution();
        List<List<Integer>> result = solution.threeSun(nums);
    }
}

class Solution {
    public List<List<Integer>> threeSun(int[] nums) {
        if (nums.length < 3) {
            return new LinkedList<>();
        }
        Arrays.sort(nums);

        int length = nums.length;
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < length ; i++) {
            if (nums[i] > 0) break;
            if( i >0 && nums[i] == nums[i-1]) continue;
            int j = i + 1;
            int k = length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                    while (j<k && nums[j] == nums[j+1])j++;
                    while (j<k && nums[k] == nums[k-1])k--;
                    j++;
                    k--;
                }else if(nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }else {
                    k--;
                }
            }

        }


        return result;
    }
}

package com.zzm.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class test {


    public static void main(String[] args) {
//        BigDecimal v = new BigDecimal(0);
//        System.out.println("-----: "+v);

//        int[] arrs = {1,2,3,4,5};
//        int[] ints = twoSum(arrs, 6);
//        for (int c = 0;c<ints.length;c++) {
//            System.out.println("-----: "+ints[c]);
//        }

        double amount = new BigDecimal((float)7200/100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("----: "+amount);
    }


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

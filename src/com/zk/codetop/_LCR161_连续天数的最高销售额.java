package com.zk.codetop;

public class _LCR161_连续天数的最高销售额 {
    public static void main(String[] args) {
        int[] sales = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSales(sales));
    }
    public static int maxSales(int[] sales) {
        int n = sales.length;
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            sum += sales[i];
            ans = Math.max(sum, ans);
            if(sum < 0){
                sum = 0;
            }
        }
        return ans;
    }
}















package com.zk.codetop;

public class _LCR127_跳跃训练 {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(trainWays(n));
    }
    public static int trainWays(int num) {
        if(num == 0){
            return 1;
        }
        int[] dp = new int[num + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= num; i++){
            dp[i] = (int)((dp[i - 1] % (1e9+7) + dp[i - 2] % (1e9+7)) % (1e9+7));
        }
        return dp[num];
    }
}






















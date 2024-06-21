package com.zk.codetop;

public class _50_Powxn {
    public static void main(String[] args) {
        double x = 2.0000;
        int n = 10;
        System.out.println(myPow(x, n));
    }
    public static double myPow(double x, int n) {
        return (long) n > 0 ? pow(x, n) : 1.0 / (pow(x, -(long) n));
    }

    private static double pow(double x, long n) {
        if(n == 0){
            return 1.0;
        }
        double y = pow(x, n / 2);
        return n % 2 == 1 ? y * y * x : y * y;
    }
}



















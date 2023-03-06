package com.wcx.excelword.demo;

public class demo1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 1};
        int k = findK(arr);
        System.out.println(k);
    }
    public static int findK(int[] arr) {
        int res = 1;
        int left = 1;
        int right = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            right *= arr[i];
            if (left == right) {
                res = i + 1;
            }
            left *= arr[i + 1];
        }
        return res;
    }
}

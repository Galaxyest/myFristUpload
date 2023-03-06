package com.wcx.excelword.demo;

import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        int n = 0;
        int[] a = new int[n];
        System.out.println("请输入数组的长度：");
        n = s1.nextInt();
        for (int i = 0;i < a.length;i++){

            System.out.println("请输入当前第" + (i+1) +"个数字");
//            Scanner s2 = new Scanner(System.in);
            int j = s1.nextInt();
            if (j == 1 || j == 2 ){
                a[i] = j;
            }else{
                System.out.println("请输入当前的元素等于1或2");
                j = s1.nextInt();
                a[i] = j;
            }

        }
        System.out.println("当前的数组为：" + a.toString() );
        int k = 0;
    }
}

package com.test.title1;

import java.util.HashSet;

import java.util.Scanner;

import java.util.Set;

/**
 * @author lxh
 * @ClassName Test.java
 * @Description 排列组合
 * @createTime 2022/7/12 20:22
 */
public class Test {
    public static Set<String> Perm(String str) {
        return perm(str, 0);
    }

    public static Set<String> perm(String str, Integer index) {
        //创建 set 集合以避免重复排列
        Set<String> permutations = new HashSet();
        //检查字符串是否为空
        if (str == null) {
            return null;
        } else if (str.length() == index) {
            //递归的终止条件
            permutations.add("");
            return permutations;
        }
        //得到第一个字符
        char first = str.charAt(index);
        //获取剩余的子字符串
        //递归调用perm
        Set<String> words = perm(str, ++index);
        //遍历 words
        for (String strNew : words) {
            for (int i = 0; i <= strNew.length(); i++) {
                //将排列插入到set集合中
                permutations.add(strNew.substring(0, i) + first + strNew.substring(i));
            }
        }
        return permutations;
    }

    public static void main(String[] args) {
        //创建scanner类的对象
        Scanner input = new Scanner(System.in);
        // 接受用户的输入
        System.out.print("输入字符串: ");
        String data = input.nextLine();
        System.out.println(data + "  的排列组合有: \n" + Perm(data));
    }

}
package com.test.title3;

import java.util.HashMap;
import java.util.Random;

/**
 * @author lxh
 * @ClassName Test.java
 * @Description 随机函数转换
 * @createTime 2022/7/1219:01
 */
public class Test {
    // 由rand5获得rand13
    public static Integer rand5ToRand13(){
        // 由rand5得到平均分布的1-25
        int t1 = rand5();
        int t2 = rand5();
        int t3 = (t1-1) * 5 + t2;   //1-25
        int t4 = t3 / 2 + 1;    //1-13
        // 其中 2-13概率都为2/25 只有1的概率为1/25 故求得一个1/25的概率事件 触发返回1
        if (rand5() + rand5() == 10){
            return 1;
        }
        return t4;
    }
    // 由rand13获得rand5
    public static Integer rand13ToRand5(){
        Integer result;
        Integer t1;
        do {
            t1 = rand13();
            result = t1/2;
        } while (t1==1||t1==12||t1==13);
        return result;
    }
    
    // 测试生成结果概率
    public static void main(String[] args) {
        HashMap<Integer, Integer> in = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            Integer integer = rand13ToRand5();
            if (in.containsKey(integer)) {
                in.put(integer, in.get(integer)+1);
            } else {
                in.put(integer, 1);
            }
            System.out.println("-"+integer+"-");
        }
        System.out.println(in);
    }


    // 已有的rand5定义
    public static Integer rand5(){
        return new Random().nextInt(5) + 1;
    }
    // 已有的rand13定义
    public static Integer rand13(){
        return new Random().nextInt(13) + 1;
    }
}

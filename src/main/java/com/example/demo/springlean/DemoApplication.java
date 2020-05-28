package com.example.demo.springlean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DemoApplication {

    private static boolean status;

    //static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static volatile int i = 0;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class);
    }

    /**
     * 找出字符串中出现次数最多的字母，次数相同的情况下返回ascii code更小的，没有字母的话返回null(忽略大小写，统一当成小写处理).
     *
     * <p>
     * 提示:
     * 1. 字符串长度在1000以内，且只包含ascii字符
     * </p>
     *
     * @param string 只包含ascii字符的字符串.
     * @return 出现次数最多的字母.
     */
//    public static Character mostFrequentLetter(String string) {
//        // TODO: 实现这个方法
//        //ascii 从0到127
//        //a-z 97~122
//        int[] count = new int[128];
//        char[] charArr = string.toCharArray();
//        for (int i = 0; i < charArr.length; i++) {
//            count[charArr[i]]++;
//        }
//
//        int preCount = 0;
//        Character output = null;
//        for (int i = 97; i < 123; i++) {
//            if (count[i] > preCount) {
//                output = (char) i;
//                preCount = count[i];
//            }
//        }
//
//        return output;
//    }

}
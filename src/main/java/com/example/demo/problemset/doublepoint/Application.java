package com.example.demo.problemset.doublepoint;


import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.commons.net.util.Base64;

/**
 * 双指针
 */
public class Application {
    public static void main(String[] args) throws Exception {

        String params="eyJyZXNlcnZlZF9ubyI6IjIwMDkwNDAwMzAwMDEiLCJwcml2aWxlZ2VfZmxhZyI6MSwic2VydmljZV9uYW1lIjoiUHJkU3VwcGx5Q2hhaW5TZXJ2aWNlIiwibWV0aG9kX25hbWUiOiJhc3luY0F1ZGl0R2VuZXJhdGVQcm9kdWN0Rm9yIn0%3d";
        String plainParams = new String((new Base64()).decode(params.getBytes()), "UTF-8");
        System.out.println(plainParams);
    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {

        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();

        int i = 0;
        int j = 0;
        while (i < s_chars.length && j < t_chars.length) {
            if (s_chars[i] == t_chars[j]) {
                i++;
            }

            j++;
        }

        return i >= s_chars.length;
    }
}

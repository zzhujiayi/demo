package com.example.demo.problemset;

import com.example.demo.problemset.design.Trie;

/**
 * @Author Zhu Jiayi
 * @Date 2021/9/16 17:52
 */
public class Application {
    public static void main(String[] args) {
        Application application = new Application();

        Trie trie = new Trie();
        trie.insert("trie");
        trie.insert("insert");
        trie.insert("apple");
        trie.insert("search");
        //trie.insert("app");
        trie.insert("insert");
        trie.insert("search");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
    }
}

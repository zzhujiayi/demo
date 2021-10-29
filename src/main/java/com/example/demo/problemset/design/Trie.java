package com.example.demo.problemset.design;

/**
 * @Author Zhu Jiayi
 * @Date 2021/9/17 10:17
 */
public class Trie {
    private Trie[] tries;

    private boolean isEnd;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        tries = new Trie[26];
        isEnd = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            Trie trie = node.tries[word.charAt(i) - 'a'];
            if (trie == null) {
                trie = new Trie();
                node.tries[word.charAt(i) - 'a'] = trie;
            }

            node = trie;
        }

        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            Trie trie = node.tries[prefix.charAt(i) - 'a'];
            if (trie == null) {
                return null;
            }

            node = trie;
        }

        return node;
    }
}

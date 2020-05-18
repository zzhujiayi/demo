package com.example.demo.problemset.instance;

import java.util.HashMap;

class LRUCache {
    static class Node {
        public int value;
        public int key;
        public Node prev;
        public Node next;
    }

    HashMap<Integer, Node> map;
    int capacity;

    Node head;
    Node tail;
    int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }

        return node.value;
    }

    private Node getNode(int key) {
        Node n = map.get(key);
        if (n == null) {
            return null;
        }

        if (n != head) {
            n.prev.next = n.next;
            if (n.next != null) {
                n.next.prev = n.prev;
            }

            if (n == tail) {
                tail = n.prev;
            }

            n.next = head;
            head.prev = n;
            n.prev = null;
            head = n;
        }

        return n;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }

        if (size >= capacity) {
            // delete tail
            int deleteKey;
            if (head == tail) {
                deleteKey = head.key;
                head = tail = null;
            } else {
                deleteKey = tail.key;
                Node prev = tail.prev;
                prev.next = null;
                tail = prev;
            }

            map.remove(deleteKey);
            size--;
        }

        node = new Node();
        node.value = value;
        node.key = key;
        map.put(key, node);
        size++;

        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
    }
}

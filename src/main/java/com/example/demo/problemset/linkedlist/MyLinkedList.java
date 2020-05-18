package com.example.demo.problemset.linkedlist;

public class MyLinkedList {

    class MyNode {
        public int val;
        public MyNode next;

        public MyNode(int val) {
            this.val = val;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int count;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (count == 0 || index < 0 || index >= count) {
            return -1;
        }


        int n = 0;
        MyNode node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }

        return node.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        MyNode node = new MyNode(val);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = head;
        }

        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        MyNode node = new MyNode(val);
        tail.next = node;
        tail = node;
        if (head == null) {
            head = tail;
        }

        count++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > count) {
            return;
        }

        if (index <= 0) {
            addAtHead(val);
        } else if (index == count) {
            addAtTail(val);
        } else {
            MyNode node = head;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }

            MyNode newNode = new MyNode(val);
            MyNode temp = node.next;
            node.next = newNode;
            newNode.next = temp;
            count++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= count) {
            return;
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        }

        MyNode node = head;
        while (index > 1 && node != null) {
            node = node.next;
            index--;
        }

        if (node != null) {
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }

            count--;
        }
    }
}

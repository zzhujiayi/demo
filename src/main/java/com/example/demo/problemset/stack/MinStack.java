package com.example.demo.problemset.stack;

import java.util.LinkedList;

public class MinStack {
    /**
     * initialize your data structure here.
     */
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> min = new LinkedList<>();

    public MinStack() {

    }

    public void push(int x) {
        stack.addFirst(x);
        if (min.size() > 0) {
            int n = min.peekFirst();
            if (n >= x) {
                min.addFirst(x);
            }
        } else {
            min.push(x);
        }
    }

    public void pop() {
        int n = stack.pop();
        if (n == min.peekFirst()) {
            min.pollFirst();
        }

    }

    public int top() {
        return stack.peekFirst();
    }

    public int getMin() {
        if (min.size() > 0) {
            return min.peekFirst();
        }

        return 0;
    }
}

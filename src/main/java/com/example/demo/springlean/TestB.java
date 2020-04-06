package com.example.demo.springlean;

public class TestB {
    private int i = 0;

    private int getI() {
        return this.i;
    }

    public void aaa() {
        synchronized (this) {
            int b = 1;
        }
    }
}

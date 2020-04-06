package com.example.demo.springlean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestVolatile {
    private boolean status;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void doWork() {
        while (status) {
            System.out.println("i'm worked.");
            status = false;
        }
    }

    public void setStatusTrue() {
        status = true;
    }
}

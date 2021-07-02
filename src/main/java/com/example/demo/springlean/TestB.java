package com.example.demo.springlean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
//@Scope("prototype")
public class TestB {

    @Autowired
    private Test test;

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

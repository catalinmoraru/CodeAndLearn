package com.springbootaop.learn.model;

import org.springframework.stereotype.Component;

@Component
public class Triangle {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}

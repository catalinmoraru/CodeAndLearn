package com.apress.prospring4.ch3.annotation;

import org.springframework.stereotype.Component;

@Component("injectSimpleConfig")
public class InjectSimpleConfig {
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }

    private String name = "John Smith";
    private int age = 35;
    private float height = 1.78f;
    private boolean programmer = true;
    private Long ageInSeconds = 1103760000L;
}


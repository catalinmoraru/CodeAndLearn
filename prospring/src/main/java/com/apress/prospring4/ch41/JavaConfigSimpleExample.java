package com.apress.prospring4.ch41;

import com.apress.prospring4.ch3.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigSimpleExample {
    public static void main(String[] args) {
        ApplicationContext ctx = new
                AnnotationConfigApplicationContext(AppConfig.class);

        MessageRenderer renderer =
                ctx.getBean("messageRenderer", MessageRenderer.class);

        renderer.render();
    }
}


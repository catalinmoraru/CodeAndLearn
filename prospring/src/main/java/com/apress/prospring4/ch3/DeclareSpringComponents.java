package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;



/*public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();

        MessageProvider messageProvider = ctx.getBean("messageProvider",
                MessageProvider.class);

        System.out.println(messageProvider.getMessage());
    }
}*/


public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer",
                MessageRenderer.class);

        messageRenderer.render();
    }
}



package com.catalin.springbootdata.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by catalin.moraru on 26.01.2017.
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHi(){
        return "Hi";
    }
}

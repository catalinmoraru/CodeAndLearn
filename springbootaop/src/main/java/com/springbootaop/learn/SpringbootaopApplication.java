package com.springbootaop.learn;

import com.springbootaop.learn.service.ShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootaopApplication {

	@Autowired
	private ShapeService shapeService;

	public static void main(String[] args) {

		SpringApplication.run(SpringbootaopApplication.class, args);

		System.out.print("Hello Srping boot");



	}
}

package com.springcourse.simpleCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SimpleCrudApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(SimpleCrudApplication.class, args);
		SpringApplication.run(SimpleCrudApplication.class, args);
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}

}

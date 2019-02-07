package com.example.reactdemo;

import com.example.reactdemo.client.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactDemoApplication.class, args);
		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getResult());
	}

}


package com.bartoszbalukiewicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AppsensorSampleAppApplication {


	public static Object mutex = new Object();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppsensorSampleAppApplication.class, args);
}

}

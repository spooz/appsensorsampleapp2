package com.bartoszbalukiewicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@PropertySource("classpath:appsensor.properties")
public class AppsensorSampleAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppsensorSampleAppApplication.class, args);
}

}

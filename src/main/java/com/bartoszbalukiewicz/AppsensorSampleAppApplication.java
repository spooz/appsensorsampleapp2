package com.bartoszbalukiewicz;

import com.bartoszbalukiewicz.appsensor.AppSensorDataGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppsensorSampleAppApplication {

	public static Object mutex = new Object();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppsensorSampleAppApplication.class, args);
		AppSensorDataGenerator simpleGenerator = context.getBean(AppSensorDataGenerator.class);
		simpleGenerator.execute();
	}

}

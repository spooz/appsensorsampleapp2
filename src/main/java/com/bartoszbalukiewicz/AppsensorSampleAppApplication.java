package com.bartoszbalukiewicz;

import com.bartoszbalukiewicz.appsensor.AppSensorDataGenerator;
import org.owasp.appsensor.core.AppSensorServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
public class AppsensorSampleAppApplication {


	public static Object mutex = new Object();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppsensorSampleAppApplication.class, args);
		for(String bean : context.getBeanDefinitionNames())
			System.out.println(bean);
		AppSensorDataGenerator simpleGenerator = context.getBean(AppSensorDataGenerator.class);
		simpleGenerator.execute();
	}

}

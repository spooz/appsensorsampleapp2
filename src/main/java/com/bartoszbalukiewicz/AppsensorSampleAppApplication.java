package com.bartoszbalukiewicz;

import com.bartoszbalukiewicz.security.AppSensorDataGenerator;
import com.bartoszbalukiewicz.security.NoopAppsensorResponseHandler;
import com.bartoszbalukiewicz.security.RestEventManager;
import org.owasp.appsensor.configuration.stax.client.StaxClientConfiguration;
import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.AppSensorServer;
import org.owasp.appsensor.core.configuration.client.ClientConfiguration;
import org.owasp.appsensor.core.event.EventManager;
import org.owasp.appsensor.core.response.ResponseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class AppsensorSampleAppApplication {

	@Bean
	public AppSensorDataGenerator appSensorDataGenerator() {
		return new AppSensorDataGenerator(eventManager());
	}

	@Bean
	public ResponseHandler responseHandler() {
		return new NoopAppsensorResponseHandler();
	}

	@Bean
	public RestEventManager eventManager() {
		return new RestEventManager();
	}

	@Bean
	public AppSensorClient appSensorClient() {
		return new AppSensorClient();
	}

	@Bean
	public ClientConfiguration clientConfiguration() {
		return new StaxClientConfiguration(true);
	}

	public static Object mutex = new Object();

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppsensorSampleAppApplication.class, args);
		AppSensorDataGenerator simpleGenerator = context.getBean(AppSensorDataGenerator.class);
		simpleGenerator.execute();
	}

}

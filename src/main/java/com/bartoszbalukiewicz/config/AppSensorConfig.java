package com.bartoszbalukiewicz.config;

import com.bartoszbalukiewicz.appsensor.AppSensorDataGenerator;
import com.bartoszbalukiewicz.appsensor.NoopAppsensorResponseHandler;
import com.bartoszbalukiewicz.appsensor.RestEventManager;
import com.bartoszbalukiewicz.security.appsensor.event.AppSensorEventListener;
import org.owasp.appsensor.configuration.stax.client.StaxClientConfiguration;
import org.owasp.appsensor.core.AppSensorClient;
import org.owasp.appsensor.core.IPAddress;
import org.owasp.appsensor.core.configuration.client.ClientConfiguration;
import org.owasp.appsensor.core.response.ResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Bartek on 26.09.2016.
 */
@Configuration
public class AppSensorConfig {

    @Bean
    public ClientConfiguration clientConfiguration() {
        return new StaxClientConfiguration(true);
    }

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

}

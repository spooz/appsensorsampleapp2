package com.bartoszbalukiewicz.config;

import org.owasp.appsensor.core.AppSensorServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by Bartek on 26.09.2016.
 */
@Configuration
@ComponentScan(value = "org.owasp.appsensor", excludeFilters = @ComponentScan.Filter(value = AppSensorServer.class, type = FilterType.ASSIGNABLE_TYPE))
public class AppSensorConfig {
}

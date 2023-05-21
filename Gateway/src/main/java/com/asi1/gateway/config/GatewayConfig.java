package com.asi1.gateway.config;

import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GatewayAutoConfiguration.class)
public class GatewayConfig {
}

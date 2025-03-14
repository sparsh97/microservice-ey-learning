package com.eazybytes.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "contact-info")
public record ContactInfoConfig(String name, String address) {
}

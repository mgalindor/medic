package com.mk.mnx;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

import com.mk.mnx.vld.starter.annotation.EnableMonoxValidator;

@SpringBootApplication
@EnableConfigurationProperties
@EnableEurekaClient
@EnableMonoxValidator
public class StartUpService {

	public static void main(String[] args) {
		SpringApplication.run(StartUpService.class, args);
	}
	
	@Bean
	public LocaleContext localeContextHolder() {
	    LocaleContextHolder.setDefaultLocale(new Locale("ES"));
	    return LocaleContextHolder.getLocaleContext();
	}
}

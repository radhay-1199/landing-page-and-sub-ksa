package com.gl.combo;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class InternationalizationConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
	
	  @Bean public LocaleResolver localeResolver() { SessionLocaleResolver
	  localeResolver = new SessionLocaleResolver();
	  localeResolver.setDefaultLocale(Locale.US); return localeResolver; }
	 
	  
	  @Bean 
	  public LocaleChangeInterceptor localeChangeInterceptor() {
	  LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor(); 
	  localeChangeInterceptor.setParamName("lang");
	  return localeChangeInterceptor; 
	  }
	  
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
		 
	  registry.addInterceptor(localeChangeInterceptor());
	  }
		
	  @Bean
	  public ResourceBundleMessageSource messageSource() {
	  ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
	  rs.setBasename("messages");
	  rs.setDefaultEncoding("UTF-8");
	  rs.setUseCodeAsDefaultMessage(true);
	  return rs;
	  }
	  

}

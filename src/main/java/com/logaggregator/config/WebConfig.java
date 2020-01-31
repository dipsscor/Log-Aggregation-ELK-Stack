package com.logaggregator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			//.allowedOrigins("http://domain2.com")
			.allowedMethods("PUT", "DELETE","GET","POST")
			//.allowedHeaders("header1", "header2", "header3")
			//.exposedHeaders("header1", "header2")
			.allowCredentials(false).maxAge(3600);

	}
	
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("swagger-ui.html")
         .addResourceLocations("classpath:/META-INF/resources/");

         registry.addResourceHandler("/webjars/**")
         .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	
	
}
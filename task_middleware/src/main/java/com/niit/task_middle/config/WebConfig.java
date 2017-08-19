package com.niit.task_middle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc  //<mvc:annotation-driven>
@ComponentScan(basePackages="com.niit") //<context:component-scan>
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver getViewReslover()
	{
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("/WEB-INF/resources/");
		
		
	}
}

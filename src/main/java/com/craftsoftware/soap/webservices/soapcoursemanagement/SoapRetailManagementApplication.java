package com.craftsoftware.soap.webservices.soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

 

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com"})
@EnableJpaRepositories("com.dataaccesslayer.repository")
@EntityScan("com.dataaccesslayer.entity")
public class SoapRetailManagementApplication extends SpringBootServletInitializer{

@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder app) { 
  return app.sources(SoapRetailManagementApplication.class); 
}

public static void main(String[] args){
SpringApplication.run(SoapRetailManagementApplication.class, args);
 }
 }
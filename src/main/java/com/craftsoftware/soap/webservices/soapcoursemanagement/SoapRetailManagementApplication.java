package com.craftsoftware.soap.webservices.soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

 

@SpringBootApplication
public class SoapRetailManagementApplication extends SpringBootServletInitializer{

@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder app) { 
  return app.sources(SoapRetailManagementApplication.class); 
}

public static void main(String[] args){
SpringApplication.run(SoapRetailManagementApplication.class, args);
 }
 }
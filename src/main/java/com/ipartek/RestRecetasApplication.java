package com.ipartek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestRecetasApplication extends SpringBootServletInitializer {

    // Este método es el que permite que el archivo .war se despliegue en Tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RestRecetasApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RestRecetasApplication.class, args);
    }
}
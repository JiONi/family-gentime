package com.wonida.gentime;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GentimeApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/app/config/family-gentime/real-application.yml";

    public static void main(String[] args) {

        new SpringApplicationBuilder(GentimeApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}

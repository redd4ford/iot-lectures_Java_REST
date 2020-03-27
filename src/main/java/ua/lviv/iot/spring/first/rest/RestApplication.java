package ua.lviv.iot.spring.first.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication is used to enable three main Spring Boot features:
// @EnableAutoConfiguration, @ComponentScan, @Configuration. but we use @ComponentScan separately
// so that the application has access to Service and Repository packages which are not accessible
// at this point (they are in a different package). the order of packages is important, because we
// create a repo (from dataaccess) BEFORE creating a service (business), therefore we need to
// access it first.

@SpringBootApplication
@ComponentScan({"ua.lviv.iot.spring.first.dataaccess",
                "ua.lviv.iot.spring.first.business",
                "ua.lviv.iot.spring.first.rest.controller"
              })
@EnableJpaRepositories({"ua.lviv.iot.spring.first.dataaccess"})
public class RestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }

}

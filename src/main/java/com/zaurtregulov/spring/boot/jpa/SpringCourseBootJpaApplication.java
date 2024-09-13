package com.zaurtregulov.spring.boot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class SpringCourseBootJpaApplication {

  private static final Logger log = LoggerFactory.getLogger(SpringCourseBootJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseBootJpaApplication.class, args);
		log.info("____________________________________________New RUN: " + new Date());
		log.error("Hello i am logger error");
	}

}

package com.zaurtregulov.spring.boot.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication // эта аннотация говорит, что этот класс является точкой входа в приложение, которое
// @ComponentScan - сканирование пакет com.zaurtregulov.spring.boot.jpa
//@Configuration - конфигурация, которая определяет спринг контейнер
//Создается spring conteiner, происходит поиск @bin и регистрация их в контейнере,
//затем запускается Tomcat сервер с настройками по умолчанию
// сканирование пакета com.zaurtregulov.spring.boot.jpa произойдет при запуске приложения

@Slf4j
public class SpringCourseBootJpaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCourseBootJpaApplication.class, args);
		log.info("____________________________________________New RUN: " + new Date());
		log.error("____________________________________________New RUN: " + new Date());
	}

}

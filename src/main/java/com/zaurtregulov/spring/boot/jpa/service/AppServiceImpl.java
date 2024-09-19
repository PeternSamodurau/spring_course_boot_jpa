package com.zaurtregulov.spring.boot.jpa.service;


import com.github.javafaker.Faker;
import com.zaurtregulov.spring.boot.jpa.dao.UserRepository;
import com.zaurtregulov.spring.boot.jpa.entity.Application;
import com.zaurtregulov.spring.boot.jpa.entity.MyUser;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class AppServiceImpl implements AppService {

    private List<Application> applicationList;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct  //аннотация, которая указывает, что метод будет вызван после инициализации конструктора
    @Override
    public void loadAppInDB() {
        Faker faker = new Faker();
        applicationList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();

        log.info("Application list loaded successfully");
    }

    @Override
    public List<Application> getApplicationList() {

        return applicationList;
    }

    @Override
    public Application getApplicationById(int id) {
        if (id < 1 || id > 100) {
            log.error("Application by id not found!");
            return null;
        } else
            log.info("Application by id found successfully: " + id);
        return applicationList.stream().filter(app -> app.getId() == id).
                findFirst().orElse(null);
    }

    @Override
    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}


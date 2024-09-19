package com.zaurtregulov.spring.boot.jpa.controller;

import com.zaurtregulov.spring.boot.jpa.entity.Application;
import com.zaurtregulov.spring.boot.jpa.entity.MyUser;
import com.zaurtregulov.spring.boot.jpa.service.AppService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
@AllArgsConstructor

public class AppController {

    private AppService appService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to unprotected page!";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Application> getAllApp() {

        return appService.getApplicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Application getAppById(@PathVariable int id) {
        return appService.getApplicationById(id);
    }
    @PostMapping("/add-user")
    public String addUser(@RequestBody MyUser user) {
        appService.addUser(user);

        return "User added successfully";
    }
}

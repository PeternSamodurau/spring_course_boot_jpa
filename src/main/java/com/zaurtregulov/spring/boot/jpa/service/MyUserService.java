package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.entity.MyUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserService {
    UserDetails loadUserByUsername(String name); // метод для получения пользователя по имени
}

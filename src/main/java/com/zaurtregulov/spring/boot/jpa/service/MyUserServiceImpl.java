package com.zaurtregulov.spring.boot.jpa.service;

import com.zaurtregulov.spring.boot.jpa.dao.UserRepository;
import com.zaurtregulov.spring.boot.jpa.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.zaurtregulov.spring.boot.jpa.config.MyUserDetails;

import java.util.Optional;

@Service
public class MyUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {

        Optional<MyUser> user = userRepository.findByName(name); // <1>

        return user.map(MyUserDetails::new).orElseThrow(() -> new RuntimeException(user + " User not found "));
    }
}

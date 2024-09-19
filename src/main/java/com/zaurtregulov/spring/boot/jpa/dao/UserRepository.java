package com.zaurtregulov.spring.boot.jpa.dao;

import com.zaurtregulov.spring.boot.jpa.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByName(String name);
}

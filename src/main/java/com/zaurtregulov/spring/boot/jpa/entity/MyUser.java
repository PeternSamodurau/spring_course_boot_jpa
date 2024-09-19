package com.zaurtregulov.spring.boot.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name",unique = true)
    private String name;

    @Column(name = "password",unique = true)
    private String password;

    @Column(name = "roles")
    private String roles;

    public MyUser(String name, String password, String roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}

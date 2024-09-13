package com.zaurtregulov.spring.boot.jpa.entity;

import lombok.Data;

@Data
public class Mail {
    private String[] to;
    public String subject;
    public String body;
}

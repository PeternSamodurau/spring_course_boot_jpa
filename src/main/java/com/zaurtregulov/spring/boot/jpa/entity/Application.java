package com.zaurtregulov.spring.boot.jpa.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Application {
    private int id;
    private String name;
    private String author;
    private String version;

}

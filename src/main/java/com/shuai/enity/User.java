package com.shuai.enity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private Long id;

    private String userName;

    private String password;

    private String name;
}

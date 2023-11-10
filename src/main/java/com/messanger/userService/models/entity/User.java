package com.messanger.userService.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 50, unique = true)
    private String email;
    private String password;

    @Column(length = 50, unique = true)
    private String uuid;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
    }
}

package com.messanger.userService.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(length = 30)
    @GeneratedValue(generator = "myStringGenerator")
    @GenericGenerator(name = "myStringGenerator", strategy = "com.messanger.userService.utils.StringIdentifierGenerator")
    private String id;

    private String name;

    @Column(name = "contact_number")
    private String contactNumber;


    public User(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }
}

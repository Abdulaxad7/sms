package com.sms.sms.user.entity;


import lombok.*;


import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Grade> grades;
}

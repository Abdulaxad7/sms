package com.sms.sms.User.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@Table(name = "courses")
@NoArgsConstructor(force = true)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String instructorName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false)
    private Double status;

}


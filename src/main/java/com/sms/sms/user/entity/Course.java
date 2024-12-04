package com.sms.sms.user.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Setter
@Getter
@Builder
@Entity
@Immutable
@AllArgsConstructor
@Table(name = "courses")
@NoArgsConstructor(force = true)
@ToString
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


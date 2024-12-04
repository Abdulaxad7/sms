package com.sms.sms.user.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@ToString
@Immutable
@AllArgsConstructor
@Table(name = "grades")
@NoArgsConstructor(force = true)
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String attendance;

    @Column(nullable = false)
    private String assignment;

    @Column(nullable = false)
    private String quiz;

    @Column(nullable = false)
    private String midTerm;

    @Column(nullable = false)
    private String finalTerm;

    @Column(nullable = false)
    private String total;

    @Column(nullable = false)
    private String grade;

}

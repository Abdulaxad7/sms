package com.sms.sms.User.entity;


import lombok.*;


import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
@Entity
@Setter
@Getter
@Table(name = "student")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // Add CascadeType.ALL to persist courses along with student
    private List<Course> courses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Grade> grades;
}

package com.kachout.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String photo;
    @Column(unique = true)
    private String code;
    private String programId;
    @OneToMany//(mappedBy = "student")
    private List<Payment> payments;

}

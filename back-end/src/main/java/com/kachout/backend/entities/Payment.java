package com.kachout.backend.entities;

import com.kachout.backend.enums.StatusPayment;
import com.kachout.backend.enums.TypePayment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor@Builder@ToString
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate paymentDate;
    private double amount;
    private String file;
    private TypePayment typePayment;
    private StatusPayment statusPayment;
    @ManyToOne
    private Student student;

}

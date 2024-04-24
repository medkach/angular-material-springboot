package com.kachout.backend.repositories;

import com.kachout.backend.enums.StatusPayment;
import com.kachout.backend.entities.Payment;
import com.kachout.backend.enums.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    public List<Payment> findByStudentCode(String studentCode);
    public List<Payment> findByTypePayment(TypePayment typePayment);
    public List<Payment> findByStatusPayment(StatusPayment statusPayment);
}

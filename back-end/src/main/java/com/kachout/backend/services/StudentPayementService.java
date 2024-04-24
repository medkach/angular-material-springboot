package com.kachout.backend.services;

import com.kachout.backend.entities.Payment;
import com.kachout.backend.entities.Student;
import com.kachout.backend.enums.StatusPayment;
import com.kachout.backend.enums.TypePayment;
import com.kachout.backend.repositories.PaymentRepository;
import com.kachout.backend.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class StudentPayementService {
    final PaymentRepository paymentRepository;
    final StudentRepository studentRepository;

    public StudentPayementService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePaymentFile(MultipartFile file, LocalDate date, double amount
            , TypePayment typePayment, String studentCode) throws IOException {
        Path directPath = Paths.get(System.getProperty("user.home"),"project_01","/PAYMENTS/");
        if(!Files.exists(directPath)){
            Files.createDirectories(directPath);
        }
        String fileName= UUID.randomUUID().toString()+".pdf";
        Path filePath=Paths.get(System.getProperty("user.home"),"project_01","/PAYMENTS/",fileName);
        Files.copy(file.getInputStream(), filePath);
        Student student=studentRepository.findByCode(studentCode);
        Payment payment=Payment
                .builder()
                .student(student)
                .paymentDate(date)
                .typePayment(typePayment)
                .amount(amount)
                .statusPayment(StatusPayment.CREATED)
                .file(filePath.toUri().toString())
                .build();
        return paymentRepository.save(payment);

    }
    public byte[] getPaymentFile(UUID id) throws IOException {
        Payment payment=paymentRepository.findById(id).orElseThrow(()->new RuntimeException("payment not exist"));
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
}

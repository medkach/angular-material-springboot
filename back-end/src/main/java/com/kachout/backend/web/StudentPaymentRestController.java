package com.kachout.backend.web;

import com.kachout.backend.entities.Payment;
import com.kachout.backend.entities.Student;
import com.kachout.backend.enums.StatusPayment;
import com.kachout.backend.enums.TypePayment;
import com.kachout.backend.repositories.PaymentRepository;
import com.kachout.backend.repositories.StudentRepository;
import com.kachout.backend.services.StudentPayementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/domain1")
@CrossOrigin("*")
public class StudentPaymentRestController {
    public final PaymentRepository paymentRepository ;
    public final StudentRepository studentRepository;
    public final StudentPayementService studentPayementService;

    public StudentPaymentRestController(PaymentRepository paymentRepository, StudentRepository studentRepository, StudentPayementService studentPayementService) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.studentPayementService = studentPayementService;
    }
@GetMapping("/students")
    public List<Student> getAllStudents()
    {
        List<Student> all = studentRepository.findAll();
        return all;
    }
    @GetMapping("/payments")
    public List<Payment> getAllPayments()
    {
        List<Payment> all = paymentRepository.findAll();
        return all;
    }
    @GetMapping("/students/{code}/payments")
    public List<Payment> getPyamentByStudentCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping("/payments/byType")
    public List<Payment> getPaymentByType(@RequestParam TypePayment typePayment){
        return paymentRepository.findByTypePayment(typePayment);
    }
    @GetMapping("/payments/byStatus")
    public List<Payment> getPaymentByStatus(@RequestParam StatusPayment statusPayment){
        return paymentRepository.findByStatusPayment(statusPayment);
    }
//    @PostMapping("/payments")
//    public Payment addPayment(@RequestBody Payment payment){
//        return paymentRepository.save(payment);
//    }
    @PutMapping("/payments")
    public Payment updateStatusPayment(@RequestParam StatusPayment statusPayment, @PathVariable UUID id){
        Payment p=paymentRepository.findById(id).orElseThrow(()->new RuntimeException("payment not exist"));
        p.setStatusPayment(statusPayment);
        return paymentRepository.save(p);
    }
    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePaymentFile(@RequestParam MultipartFile file, LocalDate date,double amount
            ,TypePayment typePayment,String studentCode) throws IOException {
       return studentPayementService.savePaymentFile(file, date, amount, typePayment, studentCode);

    }
    @GetMapping(value = "/paymentFile/{id}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable UUID id) throws IOException {
    return studentPayementService.getPaymentFile(id);
    }
}

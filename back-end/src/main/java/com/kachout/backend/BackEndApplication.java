package com.kachout.backend;

import com.kachout.backend.entities.Payment;
import com.kachout.backend.entities.Student;
import com.kachout.backend.enums.StatusPayment;
import com.kachout.backend.enums.TypePayment;
import com.kachout.backend.repositories.PaymentRepository;
import com.kachout.backend.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}
	@Bean
	 CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
		return args -> {
			TypePayment[] typePayments=TypePayment.values();
			StatusPayment[] statusPayment=StatusPayment.values();
			Random random=new Random();
			int randomIndexTypePayment = random.nextInt(typePayments.length);
			int randomIndexStatusPayment = random.nextInt(statusPayment.length);
			TypePayment typePayment = typePayments[randomIndexTypePayment];
			Student student1 = Student.builder()
					.code("code11")
					.firstName("firstName_"+random.nextInt(10))
					.lastName("lastName_"+random.nextInt(10))
					.programId("programId"+random.nextInt(10))
					.build();
			studentRepository.save(student1);
			Student student2 = Student.builder()
					.code("code22")
					.firstName("firstName_"+random.nextInt(10))
					.lastName("lastName_"+random.nextInt(10))
					.programId("programId"+random.nextInt(10))
					.build();
			studentRepository.save(student2);
			Student student3 = Student.builder()
					.code("code33")
					.firstName("firstName_"+random.nextInt(10))
					.lastName("lastName_"+random.nextInt(10))
					.programId("programId"+random.nextInt(10))
					.build();
			studentRepository.save(student3);
			Student[] students = {student1, student2,student3};
			for(Student student:students) {
				for (int i = 0; i < 5; i++) {
					paymentRepository.save(Payment.builder()
							.amount(random.nextDouble() * 10000)
							.paymentDate(LocalDate.now())
							.student(student)
								.statusPayment(statusPayment[randomIndexStatusPayment])
							.typePayment(typePayments[randomIndexTypePayment])
							.build());
				}
			}


		};
	}

}

package com.kachout.backend.repositories;

import com.kachout.backend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    public Student findByCode(String code);
    public List<Student> findByProgramId(String programId);
}

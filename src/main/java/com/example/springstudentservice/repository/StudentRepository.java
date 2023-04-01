package com.example.springstudentservice.repository;

import com.example.springstudentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStuNameContaining(String stuName);
}

package com.example.Data.Pipeline.repositories;

import com.example.Data.Pipeline.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

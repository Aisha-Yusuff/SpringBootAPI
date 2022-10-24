package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface is responsible for accessing the data in the db
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
}

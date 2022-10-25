package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.MAY;

@Configuration
public class StudentConfig {

//Params - allow it to access our db via StudentRepository class
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo) {
        return args -> {
            Student aisha = new Student(
                    "Aisha",
                    "aisha.y@gmail.com",
                    LocalDate.of(1999, MAY, 29)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(1997, MAY,4)
            );

//            Save students into our db
            repo.saveAll(
                    List.of(aisha, alex));

        };
    }
}

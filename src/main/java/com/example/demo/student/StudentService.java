package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
//        Return a list of students in the db
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
//        Validate if email entered is a valid email
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException(
                    "Student with id" + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
//        find student
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id" + studentId + " does not exists"
        ));
//        if the name of the student is not same update it with setter
        if(name != null && name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        // if the name of the student is not same update it with setter
        if(email != null && email.length() > 0 &&
        !Objects.equals(student.getEmail(), email)){
//        Check that email does not exist with another student
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("This email is taken");
            }
            student.setEmail(email);
        }

    }
}

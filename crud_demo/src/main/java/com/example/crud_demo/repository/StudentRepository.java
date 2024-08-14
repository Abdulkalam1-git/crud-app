package com.example.crud_demo.repository;

import com.example.crud_demo.dto.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByName(String name);
//    List<Student> findByRollNo(int rollNo);


}


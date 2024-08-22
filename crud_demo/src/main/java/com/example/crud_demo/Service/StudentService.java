package com.example.crud_demo.Service;

import com.example.crud_demo.repository.StudentRepository;
import com.example.crud_demo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get students by name
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    // Get student by ID
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }
    //Get student by rollNo
//     public List<Student> getStudentByrollNo(int rollNo) {
//        return studentRepository.findByRollNo(rollNo);
//     }

    // Add a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Update student by ID
    public Student updateStudent(String id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setDept(student.getDept());
            existingStudent.setEmail(student.getEmail());
            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }
    // Delete student by ID
    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }

    // Delete students by name
    public void deleteStudentsByName(String name) {
        List<Student> students = studentRepository.findByName(name);
        studentRepository.deleteAll(students);
    }

    // Delete all students
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}


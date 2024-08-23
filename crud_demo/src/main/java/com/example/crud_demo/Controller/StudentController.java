package com.example.crud_demo.Controller;

import com.example.crud_demo.Service.StudentService;
import com.example.crud_demo.dto.Student;
import com.example.crud_demo.dto.ResponseMessage;
import com.example.crud_demo.dto.ResponseMessageWithoutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Add
    @PostMapping("/create")
    public ResponseMessage<Student> saveOrUpdateStudent(@RequestBody Student student) {
        Student result;
        if (student.getId() != null && studentService.getStudentById(student.getId()) != null) {
            // Update existing student
            result = studentService.updateStudent(student.getId(), student);
            return new ResponseMessage<>("Success", "Student updated successfully", result);
        } else {
            // Add new student
            result = studentService.addStudent(student);
            return new ResponseMessage<>("Success", "Student created successfully", result);
        }
    }
    //Get
    @GetMapping("/search")
    public ResponseMessage<?> getStudents(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name
    ) {

        if (id != null) {
            // Get student by ID
            Student student = studentService.getStudentById(id);
            if (student != null) {
                return new ResponseMessage<>("Success", "Student found", student);
            } else {
                return new ResponseMessage<>("Error", "Student not found", null);
            }
        } else if (name != null) {
            // Get students by name
            List<Student> students = studentService.getStudentsByName(name);
            return new ResponseMessage<>("Success", "Students found", students);
        }
        else {
            // Get all students
            List<Student> students = studentService.getAllStudents();
            return new ResponseMessage<>("Success", "All students retrieved", students);
        }
    }
    //Delete
    @DeleteMapping()
    public ResponseMessageWithoutData deleteStudent(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) boolean deleteAll
    ) {
        if (deleteAll) {
            studentService.deleteAllStudents();
            return new ResponseMessageWithoutData("Success", "All students deleted");
        } else if (id != null) {
            if (studentService.getStudentById(id) != null) {
                studentService.deleteStudentById(id);
                return new ResponseMessageWithoutData("Success", "Student deleted with id: " + id);
            } else {
                return new ResponseMessageWithoutData("Error", "Student not found with id: " + id);
            }
        } else if (name != null) {
            studentService.deleteStudentsByName(name);
            return new ResponseMessageWithoutData("Success", "Students deleted with name: " + name);
        } else {
            return new ResponseMessageWithoutData("Error", "Invalid request. Provide id, name, or deleteAll parameter.");
        }
    }
}

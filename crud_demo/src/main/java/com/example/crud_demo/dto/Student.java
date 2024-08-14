package com.example.crud_demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "students")
public class Student {
    @Id

    private String id;
    private String name;
    private String dept;
//    private int   rollNo;
    private String email;
}

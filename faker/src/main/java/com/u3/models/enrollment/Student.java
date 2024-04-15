package com.u3.models.enrollment;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Student {

    public Student(String rm, String name, String username, String cpf, Date birth, List<String> coursesId) {
        this.rm = rm;
        this.name = name;
        this.username = username;
        this.cpf = cpf;
        this.birth = birth;
        this.coursesId = coursesId;
    }

    private String rm;

    private String name;

    private String username;

    private String cpf;

    private Date birth;

    private List<String> coursesId;

}

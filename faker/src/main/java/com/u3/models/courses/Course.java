package com.u3.models.courses;

import java.util.List;

import lombok.Data;

@Data
public class Course {

    public Course(String id, String name, List<String> studentsRm, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.studentsRm = studentsRm;
        this.subjects = subjects;
    }

    private String id;

    private String name;

    private List<String> studentsRm;

    private List<Subject> subjects;

}

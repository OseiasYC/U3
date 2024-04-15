package com.u3.models.courses;

import java.util.List;

import lombok.Data;

@Data
public class Subject {

    public Subject(String name, int workload, List<Course> courses) {
        this.name = name;
        this.workload = workload;
        this.courses = courses;
    }

    private String name;

    private int workload;

    private List<Course> courses;

}

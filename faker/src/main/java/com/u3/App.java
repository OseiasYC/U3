package com.u3;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;
import com.u3.models.courses.Course;
import com.u3.models.courses.Subject;

public class App {
    public static Faker faker = new Faker();
    public static Random randomNumber = new Random();
    public static List<String> randomRms = Generator.generateRMs(100);
    public static Map<String, Integer[]> courses = Generator.generateCourses(3, 10);

    public static void main(String[] args) {
        Generator.printMaps(courses);
    }
    
    public static void CreateCourseServiceData(int i) {
        Course course = new Course(null, faker.educator().course(), null, null);
        Subject subject = new Subject(null, 0, null);

        System.out.println(course);
    }
}

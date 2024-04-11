package com.u3;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;
import com.u3.connections.CourseServiceBatch;
import com.u3.connections.DatabaseConnection;

public class App {
    public static Faker faker = new Faker();
    public static Random randomNumber = new Random();
    public static List<String> randomRms = Generator.generateRMs(100);
    public static Map<String, Integer[]> courses = Generator.generateCourses(3, 10);
    public static DatabaseConnection CourseDbConnection = new DatabaseConnection("courses-db", "postgres", "admin");
    public static CourseServiceBatch courseServiceBatch = new CourseServiceBatch(CourseDbConnection.getConnection());

    public static void main(String[] args) {
        CourseDbConnection.getConnection();
        createCourseBatch();

    }

    public static void createCourseBatch() {
        int numberOfElements = randomNumber.nextInt(11) + 30;
        String[] studentsRm = new String[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = randomNumber.nextInt(randomRms.size());
            studentsRm[i] = randomRms.get(randomIndex);
        }

        try {
            for (Map.Entry<String, Integer[]> entry : courses.entrySet()) {
                String courseId = entry.getKey();
                String courseName = faker.educator().university();
                String subjectName = faker.educator().course();
                int subjectWorkload = randomNumber.nextBoolean() ? 60 : 40;

                courseServiceBatch.insertData(courseId, courseName, studentsRm, subjectName, subjectWorkload);
            }

            System.out.println("Inserções bem-sucedidas!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

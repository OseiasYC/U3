package com.u3;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;
import com.u3.connections.CourseServiceBatch;
import com.u3.connections.DatabaseConnection;
import com.u3.connections.EnrollmentServiceBatch;
import com.u3.connections.LibraryServiceBatch;
import com.u3.connections.RequestServiceBatch;

public class App {

    public static Faker faker = new Faker(Locale.forLanguageTag("ptBR"));
    public static Random randomNumber = new Random();

    public static List<String> randomRms = Generator.generateRMs(100);
    public static Map<String, Integer[]> courses = Generator.generateCourses(50, 10);

    public static DatabaseConnection CourseDbConnection = new DatabaseConnection("courses-db", "postgres", "admin");
    public static DatabaseConnection RequestDbConnection = new DatabaseConnection("requests-db", "postgres", "admin");
    public static DatabaseConnection EnrollmentDbConnection = new DatabaseConnection("enrollments-db", "postgres", "admin");
    public static DatabaseConnection LibraryDbConnection = new DatabaseConnection("library-db", "postgres", "admin");
    

    public static CourseServiceBatch courseServiceBatch = new CourseServiceBatch(CourseDbConnection.getConnection());
    public static RequestServiceBatch requestServiceBatch = new RequestServiceBatch(RequestDbConnection.getConnection());
    public static EnrollmentServiceBatch enrollmentServiceBatch = new EnrollmentServiceBatch(EnrollmentDbConnection.getConnection());
    public static LibraryServiceBatch libraryServiceBatch = new LibraryServiceBatch(LibraryDbConnection.getConnection());

    public static void main(String[] args) {
        // CourseDbConnection.getConnection();
        // createCourseBatch();
        // RequestDbConnection.getConnection();
        // createRequestBatch();
        // EnrollmentDbConnection.getConnection();
        // createEnrollmentBatch();
        // LibraryDbConnection.getConnection();
        // createLibraryBatch();
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

    public static void createEnrollmentBatch() {

        try {
            for (int i = 0; i < 10; i++) {
                String rm = randomRms.get(randomNumber.nextInt(randomRms.size()));
                String name = faker.name().fullName();
                String username = faker.name().username();
                String cpf = faker.numerify("###########");
                Date birth = faker.date().birthday();

                String[] coursesId = new String[1];
                coursesId[0] = courses.keySet().toArray()[randomNumber.nextInt(courses.size())].toString();

                enrollmentServiceBatch.insertData(rm, name, username.substring(0, Math.min(username.length(), 20)), cpf,
                        birth, coursesId);
            }

            System.out.println("Inserções bem-sucedidas!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createLibraryBatch() {
        String[] loanStatuses = { "BORROWED", "RETURNED", "OVERDUE", "LOST" };

        try {
            for (int i = 0; i < 10; i++) {
                String title = faker.book().title();
                String author = faker.book().author();
                Integer amount = randomNumber.nextInt(10);
                
                String studentRm = randomRms.get(randomNumber.nextInt(randomRms.size()));
                Long bookId = (long) randomNumber.nextInt(10);
                String loanStatus = loanStatuses[randomNumber.nextInt(loanStatuses.length)];
                LocalDateTime loanDate = LocalDateTime.now();
                LocalDateTime returnDate = LocalDateTime.now().plusDays(7);

                libraryServiceBatch.insertData(studentRm, bookId, loanStatus, loanDate, returnDate, title, author, amount);
            }

            System.out.println("Inserções bem-sucedidas!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createRequestBatch() {
        String[] statuses = { "OPEN", "CONCLUDED", "CANCELED" };

        try {
            for (int i = 0; i < 1; i++) {
                String studentRm = randomRms.get(randomNumber.nextInt(randomRms.size()));
                String title = faker.lorem().sentence();
                String description = faker.lorem().paragraph();
                byte[] attachment = Generator.generateRandomFile();
                LocalDateTime requestDate = LocalDateTime.now();
                String status = statuses[randomNumber.nextInt(statuses.length)];

                requestServiceBatch.insertData(studentRm, title, description,
                        attachment, requestDate, status);
            }

            System.out.println("Inserções bem-sucedidas!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

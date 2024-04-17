package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.ResultSet;

public class CourseServiceBatch {

    Connection connection;
    // Integer count = 0;
    // Long[] subjectId = new Long[5];

    public CourseServiceBatch(Connection connection) {
        this.connection = connection;
    }

    public void insertData(String courseId, String courseName, String[] studentRms, String subjectName,
            int subjectWorkload) throws SQLException {
        String insertCourseQuery = "INSERT INTO course (id, name, students_rm) VALUES (?, ?, ?)";

        PreparedStatement courseStatement = connection.prepareStatement(insertCourseQuery);
        courseStatement.setString(1, courseId);
        courseStatement.setString(2, courseName);
        courseStatement.setArray(3, connection.createArrayOf("VARCHAR", studentRms));
        courseStatement.executeUpdate();

        String insertSubjectQuery = "INSERT INTO subject (name, workload) VALUES (?, ?) RETURNING id";

        try (PreparedStatement subjectStatement = connection.prepareStatement(insertSubjectQuery)) {
            subjectStatement.setString(1, subjectName);
            subjectStatement.setInt(2, subjectWorkload);

            try (ResultSet rs = subjectStatement.executeQuery()) {
                if (rs.next()) {
                    Long subjectId = rs.getLong("id");

                    String insertCourseSubjectQuery = "INSERT INTO course_subject (course_id, subject_id) VALUES (?, ?)";
                    try (PreparedStatement courseSubjectStatement = connection
                            .prepareStatement(insertCourseSubjectQuery)) {
                        courseSubjectStatement.setString(1, courseId);
                        courseSubjectStatement.setLong(2, subjectId);
                        courseSubjectStatement.executeUpdate();
                    }
                } else {
                    System.err.println("Nenhum ID retornado pela inserção na tabela subject.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a inserção: " + e.getMessage());
        }

    }

    // public void insertData2(String courseId, String courseName, String[] studentRms, String subjectName,
    //         int subjectWorkload) throws SQLException {
    //     String insertCourseQuery = "INSERT INTO course (id, name, students_rm) VALUES (?, ?, ?)";

    //     PreparedStatement courseStatement = connection.prepareStatement(insertCourseQuery);
    //     courseStatement.setString(1, courseId);
    //     courseStatement.setString(2, courseName);
    //     courseStatement.setArray(3, connection.createArrayOf("VARCHAR", studentRms));
    //     courseStatement.executeUpdate();

    //     String insertSubjectQuery = "INSERT INTO subject (name, workload) VALUES (?, ?) RETURNING id";

    //     PreparedStatement subjectStatement = connection.prepareStatement(insertSubjectQuery);
    //     subjectStatement.setString(1, subjectName);
    //     subjectStatement.setInt(2, subjectWorkload);
    //     ResultSet rs = subjectStatement.executeQuery();
    //     count++;

    //     try {
    //         if (rs.next()) {
    //             subjectId[count] = rs.getLong("id");
    //         } else {
    //             System.err.println("Nenhum ID retornado pela inserção na tabela subject.");
    //         }

    //         if (count % 5 == 0) {
    //             String insertCourseSubjectQuery = "INSERT INTO course_subject (course_id, subject_id) VALUES (?, ?)";
    //             PreparedStatement courseSubjectStatement = connection.prepareStatement(insertCourseSubjectQuery);
    //             courseSubjectStatement.setString(1, courseId);
    //             courseSubjectStatement.setArray(2, connection.createArrayOf("Long", Arrays.copyOf(subjectId, count)));
    //             courseSubjectStatement.executeUpdate();
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

}

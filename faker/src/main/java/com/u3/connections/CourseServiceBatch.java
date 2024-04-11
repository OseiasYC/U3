package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseServiceBatch {

    Connection connection;

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

        String insertSubjectQuery = "INSERT INTO subject (name, workload) VALUES (?, ?)";

        PreparedStatement subjectStatement = connection.prepareStatement(insertSubjectQuery);
        subjectStatement.setString(1, subjectName);
        subjectStatement.setInt(2, subjectWorkload);
        subjectStatement.executeUpdate();
    }
}

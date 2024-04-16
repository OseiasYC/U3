package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class GradesServiceBatch {

    Connection connection;

    public GradesServiceBatch(Connection connection) {
        this.connection = connection;
    }

    public void insertData(String studentRm, String courseId, double totalCourseWorkloadPercentage,
            double globalAverage, String situation, String shift,
            Date courseEntryDate, Integer totalSubjectWorkload, Double[] grades, Integer abscences,
            Long studentSummaryId, Date subjectEntryDate) throws SQLException {

        String insertStudentSummaryQuery = "INSERT INTO student_summary (student_rm, course_id, total_course_workload_percentage, global_average, situation, shift, course_entry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement studentSummaryStatement = connection.prepareStatement(insertStudentSummaryQuery);
        studentSummaryStatement.setString(1, studentRm);
        studentSummaryStatement.setString(2, courseId);
        studentSummaryStatement.setDouble(3, totalCourseWorkloadPercentage);
        studentSummaryStatement.setDouble(4, globalAverage);
        studentSummaryStatement.setString(5, situation);
        studentSummaryStatement.setString(6, shift);
        studentSummaryStatement.setObject(7, courseEntryDate, java.sql.Types.DATE);
        studentSummaryStatement.executeUpdate();

        String insertStudentSubjectGradesQuery = "INSERT INTO student_subject_grades (course_id, situation, total_subject_workload, grades, abscences, student_summary_id, subject_entry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement studentSubjectGradesStatement = connection.prepareStatement(insertStudentSubjectGradesQuery);
        studentSubjectGradesStatement.setString(1, courseId);
        studentSubjectGradesStatement.setString(2, situation);
        studentSubjectGradesStatement.setInt(3, totalSubjectWorkload);
        studentSubjectGradesStatement.setArray(4, connection.createArrayOf("DOUBLE", grades));
        studentSubjectGradesStatement.setInt(5, abscences);
        studentSubjectGradesStatement.setLong(6, studentSummaryId);
        studentSubjectGradesStatement.setObject(7, subjectEntryDate, java.sql.Types.DATE);
        studentSubjectGradesStatement.executeUpdate();
    }
}

package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class EnrollmentServiceBatch {

    Connection connection;

    public EnrollmentServiceBatch(Connection connection) {
        this.connection = connection;
    }

    public void insertData(String rm, String name, String username, String cpf, Date birth, String[] coursesId)
            throws SQLException {
        String insertEnrollmentQuery = "INSERT INTO student (rm, name, username, cpf, birth, courses_id) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement enrollmentStatement = connection.prepareStatement(insertEnrollmentQuery);
        enrollmentStatement.setString(1, rm);
        enrollmentStatement.setString(2, name);
        enrollmentStatement.setString(3, username);
        enrollmentStatement.setString(4, cpf);
        enrollmentStatement.setObject(5, birth, java.sql.Types.DATE);
        enrollmentStatement.setArray(6, connection.createArrayOf("VARCHAR", coursesId));
        enrollmentStatement.executeUpdate();
    }
}

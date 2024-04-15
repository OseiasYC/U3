package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class RequestServiceBatch {

    Connection connection;

    public RequestServiceBatch(Connection connection) {
        this.connection = connection;
    }

    public void insertData(String studentRm, String title, String description,
            byte[] attachment, LocalDateTime requestDate, String status) throws SQLException {
        String insertRequestQuery = "INSERT INTO request (student_rm, title, description, attachment, request_date, status) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement requestStatement = connection.prepareStatement(insertRequestQuery);
        requestStatement.setString(1, studentRm);
        requestStatement.setString(2, title);
        requestStatement.setString(3, description);
        requestStatement.setBytes(4, attachment);
        requestStatement.setObject(5, requestDate);
        requestStatement.setString(6, status);
        requestStatement.executeUpdate();
    }

}

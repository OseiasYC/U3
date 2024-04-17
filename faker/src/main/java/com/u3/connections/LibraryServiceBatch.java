package com.u3.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LibraryServiceBatch {

    Connection connection;

    public LibraryServiceBatch(Connection connection) {
        this.connection = connection;
    }

    public void insertData(String studentRm, String loanStatus, LocalDateTime loanDate,
            LocalDateTime returnDate, String title, String author, Integer amount) throws SQLException {
        String insertBookQuery = "INSERT INTO book (title, author, amount) VALUES (?, ?, ?) RETURNING id";

        try (PreparedStatement bookStatement = connection.prepareStatement(insertBookQuery)) {
            bookStatement.setString(1, title);
            bookStatement.setString(2, author);
            bookStatement.setInt(3, amount);

            try (ResultSet rs = bookStatement.executeQuery()) {
                if (rs.next()) {
                    Long bookId = rs.getLong("id");

                    String insertLoanQuery = "INSERT INTO loan (student_rm, book_id, loan_status, loan_date, return_date) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement loanStatement = connection.prepareStatement(insertLoanQuery)) {
                        loanStatement.setString(1, studentRm);
                        loanStatement.setLong(2, bookId);
                        loanStatement.setString(3, loanStatus);
                        loanStatement.setObject(4, loanDate);
                        loanStatement.setObject(5, returnDate);
                        loanStatement.executeUpdate();
                    }
                } else {
                    System.err.println("Nenhum ID retornado pela inserção na tabela book.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a inserção: " + e.getMessage());
        }

    }

}

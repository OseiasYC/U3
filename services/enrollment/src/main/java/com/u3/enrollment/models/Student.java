package com.u3.enrollment.models;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @Column(length = 10)
    @Nonnull
    private String rm;

    private String name;

    @Column(length = 20)
    private String username;

    @Nonnull
    @Column(length = 11)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birth;

    private List<String> coursesId;

}

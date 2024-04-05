package com.u3.requests.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.u3.requests.enums.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentRm;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    private byte[] attachment;

    @CreationTimestamp
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}

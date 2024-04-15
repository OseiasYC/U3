package com.u3.models.request;

import java.time.LocalDateTime;

import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class Request {

    public Request(String studentRm, String title, String description, byte[] attachment, LocalDateTime requestDate,
            String status) {
        this.studentRm = studentRm;
        this.title = title;
        this.description = description;
        this.attachment = attachment;
        this.requestDate = requestDate;
        this.status = status;
    }

    private String studentRm;

    private String title;

    private String description;

    private byte[] attachment;

    @CreationTimestamp
    private LocalDateTime requestDate;

    private String status;

}

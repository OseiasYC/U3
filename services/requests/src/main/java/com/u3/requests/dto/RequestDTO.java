package com.u3.requests.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.u3.requests.enums.RequestStatus;

public record RequestDTO(
    Long id,
    String studentRm,
    String title,
    String description,
    MultipartFile attachment,
    LocalDateTime requestDate,
    RequestStatus status
) {
    
}

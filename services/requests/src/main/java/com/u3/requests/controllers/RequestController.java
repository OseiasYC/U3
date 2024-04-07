package com.u3.requests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.requests.dto.RequestDTO;
import com.u3.requests.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {
    
    @Autowired
    RequestService requestService;

    @GetMapping("/all")
    public List<RequestDTO> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable("id") Long id) {
        try {
            RequestDTO requestDTO = requestService.getRequestById(id);
            return ResponseEntity.ok(requestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestDTO requestDTO) {
        RequestDTO savedRequest = requestService.saveRequest(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

}

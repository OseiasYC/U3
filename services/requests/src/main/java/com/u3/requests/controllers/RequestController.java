package com.u3.requests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.u3.requests.enums.RequestStatus;
import com.u3.requests.models.Request;
import com.u3.requests.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(requestService.getRequest(requestId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @GetMapping("/student/{studentRm}")
    public ResponseEntity<List<Request>> getRequestsByStudentRm(@PathVariable String studentRm) {
        return ResponseEntity.ok(requestService.getRequestsByStudentRm(studentRm));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Request>> getRequestsByStatus(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(requestService.getRequestsByStatus(status));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@RequestBody Request request,
            UriComponentsBuilder uriComponentsBuilder) {
        requestService.createRequest(request);
        var uri = uriComponentsBuilder.path("/request/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body("Request created successfully");
    }

    @PutMapping("/update/{requestId}")
    public ResponseEntity<String> updateRequest(@RequestBody Request request, @PathVariable Long requestId) {
        requestService.updateRequest(request);
        return ResponseEntity.ok("Request updated successfully");
    }

    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.ok("Request deleted successfully");
    }

}

package com.u3.requests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.requests.enums.RequestStatus;
import com.u3.requests.models.Request;
import com.u3.requests.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("/{requestId}")
    public Request getRequest(@PathVariable Long requestId) {
        return requestService.getRequest(requestId);
    }

    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/student/{studentRm}")
    public List<Request> getRequestsByStudentRm(@PathVariable String studentRm) {
        return requestService.getRequestsByStudentRm(studentRm);
    }

    @GetMapping("/status/{status}")
    public List<Request> getRequestsByStatus(@PathVariable RequestStatus status) {
        return requestService.getRequestsByStatus(status);
    }

    @PostMapping("/create")
    public void createRequest(@RequestBody Request request) {
        requestService.createRequest(request);
    }

    @PutMapping("/update/{requestId}")
    public void updateRequest(@RequestBody Request request, @PathVariable Long requestId) {
        requestService.updateRequest(request);
    }

    @DeleteMapping("/delete/{requestId}")
    public void deleteRequest(@PathVariable Long requestId) {
        requestService.deleteRequest(requestId);
    }

}

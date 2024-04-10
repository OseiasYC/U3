package com.u3.requests.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.requests.enums.RequestStatus;
import com.u3.requests.interfaces.RequestRepository;
import com.u3.requests.models.Request;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public void createRequest(Request request) {
        requestRepository.save(request);
    }

    public void updateRequest(Request request) {
        requestRepository.save(request);
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getRequestsByStudentRm(String studentRm) {
        return requestRepository.findByStudentRm(studentRm);
    }

    public List<Request> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findByStatus(status);
    }

}

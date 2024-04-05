package com.u3.requests.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.requests.interfaces.RequestRepository;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;
    
    public void findAll() {
        requestRepository.findAll();
    }
}

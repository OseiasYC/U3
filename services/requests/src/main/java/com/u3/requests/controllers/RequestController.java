package com.u3.requests.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u3.requests.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {
    
    @Autowired
    RequestService requestService;

    @GetMapping("/findAll")
    public void findAll() {
        requestService.findAll();
    }
}

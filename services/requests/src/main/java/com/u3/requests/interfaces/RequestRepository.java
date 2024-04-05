package com.u3.requests.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.requests.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    
}

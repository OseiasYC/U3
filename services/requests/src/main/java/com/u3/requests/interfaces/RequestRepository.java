package com.u3.requests.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.u3.requests.enums.RequestStatus;
import com.u3.requests.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByStudentRm(String studentRm);

    List<Request> findByStatus(RequestStatus status);

}

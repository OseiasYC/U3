package com.u3.requests.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.u3.requests.dto.RequestDTO;
import com.u3.requests.interfaces.RequestRepository;
import com.u3.requests.models.Request;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public List<RequestDTO> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO getRequestById(Long id) {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isPresent()) {
            return convertToDTO(requestOptional.get());
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }

    public RequestDTO saveRequest(RequestDTO requestDTO) {
        Request request = convertToEntity(requestDTO);
        Request savedRequest = requestRepository.save(request);
        return convertToDTO(savedRequest);
    }

    private RequestDTO convertToDTO(Request request) {
        return new RequestDTO(
                request.getId(),
                request.getStudentRm(),
                request.getTitle(),
                request.getDescription(),
                null, //FIXME
                request.getRequestDate(),
                request.getStatus());
    }

    private Request convertToEntity(RequestDTO requestDTO) {
        Request request = new Request();
        request.setId(requestDTO.id());
        request.setStudentRm(requestDTO.studentRm());
        request.setTitle(requestDTO.title());
        request.setDescription(requestDTO.description());
        // FIXME: request.setAttachment(requestDTO.attachment());
        request.setRequestDate(requestDTO.requestDate());
        request.setStatus(requestDTO.status());
        return request;
    }

}

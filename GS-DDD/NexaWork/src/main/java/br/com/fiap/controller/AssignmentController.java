package br.com.fiap.controller;

import br.com.fiap.api.AssignmentApi;
import br.com.fiap.api.dto.AssignmentDTO;
import br.com.fiap.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController implements AssignmentApi {

    @Autowired
    private AssignmentService service;

    @Override
    public ResponseEntity<Void> createAssignment(AssignmentDTO request) {
        service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateAssignment(String id, AssignmentDTO request) {
        service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<AssignmentDTO>> findtUserAssignment(String userId) {
        List<AssignmentDTO> response = service.findByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<AssignmentDTO>> findTrailAssignment(String trailId) {
        List<AssignmentDTO> response = service.findByTrailId(trailId);
        return ResponseEntity.ok(response);
    }
}

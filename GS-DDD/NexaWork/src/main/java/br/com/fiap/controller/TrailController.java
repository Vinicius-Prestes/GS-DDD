package br.com.fiap.controller;

import br.com.fiap.api.TrailApi;
import br.com.fiap.domain.Trail;
import br.com.fiap.service.TrailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrailController implements TrailApi {

    @Autowired
    private final TrailService service;

    @Override
    public ResponseEntity<Void> createTrail(Trail request) {
        service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Trail> findTrail(String id) {
        Trail response = service.findTrail(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<Trail>> findAllTrail() {
        List<Trail> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateTrail(String id, Trail request) {
        service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteTrail(String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

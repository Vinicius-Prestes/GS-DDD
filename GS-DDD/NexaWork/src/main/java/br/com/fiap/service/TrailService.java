package br.com.fiap.service;

import br.com.fiap.domain.Trail;
import br.com.fiap.infrastructure.exception.ResourceNotFoundException;
import br.com.fiap.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailService {

    @Autowired
    private TrailRepository repository;

    public void create(Trail trail) {
        if (trail.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new trail creation");
        }
        repository.save(trail);
    }

    public void update(String id, Trail trail) {
        if (trail.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for trail update");
        }

        if (Long.parseLong(id) != trail.getId()) {
            throw new IllegalArgumentException(
                    "The informed assignmentId: " + id + " differs from the assignmentId: " + trail.getId());
        }

        if (!repository.existsById(trail.getId())) {
            throw new ResourceNotFoundException("Trail not found with id: " + trail.getId());
        }

        repository.save(trail);
    }

    public void delete(String id) {
        Long trailId = Long.parseLong(id);
        if (!repository.existsById(trailId)) {
            throw new ResourceNotFoundException("Trail not found with id: " + id);
        }
        repository.deleteById(trailId);
    }

    public List<Trail> findAll() {
        return repository.findAll();
    }

    public Trail findTrail(String id) {
        Long trailId = Long.parseLong(id);
        return repository.findById(trailId)
                .orElseThrow(() -> new ResourceNotFoundException("Trail not found with id: " + id));
    }
}
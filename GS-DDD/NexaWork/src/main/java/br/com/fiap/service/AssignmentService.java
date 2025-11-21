package br.com.fiap.service;

import br.com.fiap.api.dto.AssignmentDTO;
import br.com.fiap.domain.Assignment;
import br.com.fiap.domain.Trail;
import br.com.fiap.domain.User;
import br.com.fiap.infrastructure.exception.ResourceNotFoundException;
import br.com.fiap.repository.AssignmentRepository;
import br.com.fiap.repository.TrailRepository;
import br.com.fiap.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrailRepository trailRepository;

    public void create(AssignmentDTO assignmentDTO) {
        if (assignmentDTO.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new assignment creation");
        }
        if(!userRepository.existsById(assignmentDTO.getUserId())){
            throw new ResourceNotFoundException("User not found with id: " + assignmentDTO.getUserId());
        }
        if(!trailRepository.existsById(assignmentDTO.getTrailId())){
            throw new ResourceNotFoundException("Trail not found with id: " + assignmentDTO.getTrailId());
        }

        if (assignmentRepository.existsByUserIdAndTrailId(assignmentDTO.getUserId(), assignmentDTO.getTrailId())) {
            throw new IllegalArgumentException("Assignment already exists for user id: " +
                    assignmentDTO.getUserId() + " and trail id: " + assignmentDTO.getTrailId());
        }

        if (assignmentDTO.getStatus() == null) {
            assignmentDTO.setStatus(Assignment.Status.ACTIVE.toString());
        }

        Assignment assignment = dtoToModel(assignmentDTO);


        assignmentRepository.save(assignment);
    }

    public void update(String id, AssignmentDTO assignmentDTO) {
        if (assignmentDTO.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for assignment update");
        }

        if (Long.parseLong(id) != assignmentDTO.getId()) {
            throw new IllegalArgumentException("The informed assignmentId:" + id + " differs from the assignmentId:" + assignmentDTO.getId());
        }

        if (!assignmentRepository.existsById(assignmentDTO.getId())) {
            throw new ResourceNotFoundException("Assignment not found with id: " + assignmentDTO.getId());
        }

        Assignment existingAssignment = assignmentRepository.findById(assignmentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentDTO.getId()));

        existingAssignment.setStatus(Assignment.Status.parse(assignmentDTO.getStatus()));

        assignmentRepository.save(existingAssignment);
    }

    public List<AssignmentDTO> findByUserId(String userId) {
        Long user = Long.parseLong(userId);
        List<Assignment> response = assignmentRepository.findByUserId(user);
        return response.stream()
                .map(AssignmentDTO::new)
                .toList();
    }

    public List<AssignmentDTO> findByTrailId(String trailId) {
        Long trail = Long.parseLong(trailId);
        List<Assignment> response = assignmentRepository.findByTrailId(trail);
        return response.stream()
                .map(AssignmentDTO::new)
                .toList();
    }

    private Assignment dtoToModel(AssignmentDTO assignmentDTO){
        User user = userRepository.getReferenceById(assignmentDTO.getUserId());
        Trail trail = trailRepository.getReferenceById(assignmentDTO.getTrailId());

        return Assignment.builder()
                .id(assignmentDTO.getId())
                .user(user)
                .trail(trail)
                .status(Assignment.Status.parse(assignmentDTO.getStatus()))
                .build();
    }

}

package br.com.fiap.repository;

import br.com.fiap.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByUserId(Long userId);
    boolean existsByUserIdAndTrailId(Long userId, Long trailId);
    List<Assignment> findByTrailId(Long trailId);
}

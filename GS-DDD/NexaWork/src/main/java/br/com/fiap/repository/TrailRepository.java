package br.com.fiap.repository;

import br.com.fiap.domain.Trail;
import br.com.fiap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<Trail, Long> {
}

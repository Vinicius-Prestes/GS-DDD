package br.com.fiap.service;

import br.com.fiap.domain.User;
import br.com.fiap.infrastructure.exception.ResourceNotFoundException;
import br.com.fiap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void create(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new user creation");
        }
        repository.save(user);
    }

    public void update(String id, User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for user update");
        }

        if (Long.parseLong(id) != user.getId()){
            throw new IllegalArgumentException("The informed userId:"+id+" differs to the userId:"+user.getId());
        }

        if (!repository.existsById(user.getId())) {
            throw new ResourceNotFoundException("User not found with id: " + user.getId());
        }

        repository.save(user);
    }

    public void delete(String id) {
        Long userId = Long.parseLong(id);
        if (!repository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        repository.deleteById(userId);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findUser(String id) {
        Long userId = Long.parseLong(id);
        return repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

}

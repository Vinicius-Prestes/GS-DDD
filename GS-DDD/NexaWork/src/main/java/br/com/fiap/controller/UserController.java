package br.com.fiap.controller;

import br.com.fiap.api.UserApi;
import br.com.fiap.domain.User;
import br.com.fiap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    @Autowired
    private final UserService service;

    @Override
    public ResponseEntity<Void> createUser(User request) {
        service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<User> findUser(String id) {
        User response = service.findUser(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<User>> findAllUser() {
        List<User> response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, User request) {
        service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

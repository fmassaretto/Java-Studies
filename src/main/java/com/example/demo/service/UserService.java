package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.requests.UserRequest;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UsersRepository repo;
    private final UserMapper mapper;

    public UserService(UsersRepository repo, UserMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserByUUID(UUID uuid) {
        return repo.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(UserRequest userRequest) {
        User userMapper = mapper.mapUserRequestToUser(userRequest);

        return repo.save(userMapper);
    }

    public User updateUser(UserRequest userRequest) {
        User userMapper = mapper.mapUserRequestToUser(userRequest);

        return repo.save(userMapper);
    }

    public void deleteUser(UUID uuid) {
        repo.deleteById(uuid);
    }

    public void makeTransfer(User fromUser, User toUser, BigDecimal amount) {
        User newFromUser = new User();
        newFromUser.userUUID = fromUser.userUUID;
        newFromUser.username = fromUser.username;
        newFromUser.password = fromUser.password;
        newFromUser.email = fromUser.email;
        newFromUser.cpf = fromUser.cpf;
        newFromUser.balance = fromUser.balance.subtract(amount);
        newFromUser.userType = fromUser.userType;

        User newtoUser = new User();
        newtoUser.userUUID = toUser.userUUID;
        newtoUser.username = toUser.username;
        newtoUser.password = toUser.password;
        newtoUser.email = toUser.email;
        newtoUser.cpf = toUser.cpf;
        newtoUser.balance = toUser.balance.add(amount);
        newtoUser.userType = toUser.userType;

        repo.save(newFromUser);
        repo.save(newtoUser);
    }

}

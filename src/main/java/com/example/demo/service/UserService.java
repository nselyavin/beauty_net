package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.Roles;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(UserDTO userIn){
        User userFromDB = userRepository.findUserByUsername(userIn.getUsername());

        if(userFromDB  != null){
            LOG.error("The username {" + userIn.getUsername() +  "} already exist. Please check credentials");
           throw new RuntimeException("User not found");
        }

        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setLastName(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Roles.ROLE_USER);

        user.setRole(roles);

        return userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
//    public User updateUser(UserDTO userDTO, User userIn){
//        User user = userRepository.findUserByUsername(userIn.getName());
//        user.setName(userDTO.getName());
//        user.setLastName(userDTO.getLastName());
//
//        return userRepository.save(user);
//    }

}

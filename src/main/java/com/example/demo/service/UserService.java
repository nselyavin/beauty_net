package com.example.demo.service;

import com.example.demo.entity.enums.ERole;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;


    public int createUser(User userIn){
        User userFromDB = userRepository.findByUsername(userIn.getUsername());

        if(userFromDB  != null){
            LOG.error("The username {" + userIn.getUsername() +  "} already exist. Please check credentials");
            return 1;
        }

        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setLastName(userIn.getLastName());
        user.setUsername(userIn.getUsername());
        user.setPassword(userIn.getPassword());
        user.getRoles().add(ERole.ROLE_USER);

        userRepository.save(userIn);

        return 0;
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

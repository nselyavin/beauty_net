package ru.fume.beautynet.entity;

import ru.fume.beautynet.entity.enums.ERole;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class User {
    private Long id;
    private String name;
    private String username;
    private String lastname;
    private String email;
    private String about;
    private String password;

    private Set<ERole> role = new HashSet<>();
    private LocalDateTime createdData;

    @PrePersist
    protected void onCreate(){
        this.createdData = LocalDateTime.now();
    }
}

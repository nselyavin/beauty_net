package ru.fume.beautynet.entity;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {
    private Long id;
    private String title;
    private String localtime;
    private Integer likes;
    private Float rating;
    private Brand creator;

    private Set<String> tags = new HashSet<>();
    private Set<String> likedUsers = new HashSet<>();
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createdData;

    @PrePersist
    protected void onCreate(){
        this.createdData = LocalDateTime.now();
    }

}

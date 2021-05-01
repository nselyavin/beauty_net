package ru.fume.beautynet.entity;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Post post;
    private String username;
    private Long userId;
    private String message;

    private LocalDateTime createdData;

    @PrePersist
    protected void onCreate(){
        this.createdData = LocalDateTime.now();
    }
}

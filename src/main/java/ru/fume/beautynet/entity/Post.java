package ru.fume.beautynet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(updatable = false)
    private String localtime;
    @Column(columnDefinition = "0")
    private Integer likes;
    @Column(columnDefinition = "-1")
    private Float rating;


    public Post() {
    }

    // У одного поста, один создатель
    @ManyToOne(fetch =  FetchType.LAZY)
    private User user;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> tags = new HashSet<>();
    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createdData;

    @PrePersist
    protected void onCreate(){
        this.createdData = LocalDateTime.now();
    }

}

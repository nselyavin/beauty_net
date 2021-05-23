package com.example.demo.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String description;
    private String brand;
    //@Column(columnDefinition = "0")
    // ToDo возможность ставить лайки и дизлайки
    private Integer likes = 0;
    //@Column(columnDefinition = '0')
    private Integer dislikes = 0;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> tags = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();
    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> dislikedUsers = new HashSet<>();

    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Post(){

    }

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }


    public String getEncodedImage() {
        if(image!=null && image.length>0) {
            String encodeBase64 = Base64.getEncoder().encodeToString(image);
            String imageEncodeBase64 = new String(encodeBase64);
            return imageEncodeBase64;
        }
        else
            return "";
    }
}

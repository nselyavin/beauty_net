package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);

    List<Post> findAllByUsernameOrderByCreatedDateDesc(String username);

    List<Post> findAllByOrderByCreatedDateDesc();

    Post findPostByIdAndUsername(Long id, String username);

}

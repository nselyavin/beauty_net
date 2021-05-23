package com.example.demo.service;


import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private Logger LOG = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public Comment createComment(long postId, CommentDTO commentDTO) {
        Post post = postRepository.findPostById(postId);

        Comment comment = new Comment();
        comment.setUsername(commentDTO.getUsername());
        comment.setMessage(commentDTO.getMessage());
        comment.setPost(post);

        LOG.info("Saving comment for Post: {}", post.getId());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsForPost(long postId){
        Post post  = postRepository.getById(postId);

        if(post == null){
            LOG.error("Not found post by {} id", postId);
            throw new RuntimeException("Not found post");
        }

        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments;
    }
}

package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PostService {
    private final PostRepository postRepository;
    private Logger LOG = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(Long postId) {
        Post post = postRepository.findPostById(postId);
        if(post == null){
            LOG.error("Post by {} id, not found", postId);
            throw new RuntimeException("Post not found");
        }

        return post;
    }


    public Post createPost(PostDTO postDTO){
        Post post = new Post();
        post.setUsername(postDTO.getUsername());
        post.setDescription(postDTO.getDescription());
        post.setBrand(postDTO.getBrand());
        post.setTitle(postDTO.getTitle());
        try {
            post.setImage(postDTO.getImage().getBytes());
        }catch (Exception e){
            LOG.error("Failed upload image");
            post.setImage(null);
        }
        System.out.println();
        post.setTags(getTagsFromStr(postDTO.getTagsStr()));

        System.out.println(post.getTags());
        LOG.info("Saving Post for User: {}", postDTO.getUsername());
        return postRepository.save(post);
    }

    public List<Post> getLastPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedDateDesc();
        if(posts.size() == 0){
            LOG.error("No one post in repository");
        }

        List<Post> lastPosts = new ArrayList<>();

        for (int i = 0; i < 50 && i < posts.size(); i++){
            lastPosts.add(posts.get(i));
        }

        return lastPosts;
    }

    public Post likePost(Long postId, String username){
        Post post = postRepository.findPostById(postId);

        if(post == null){
            LOG.error("Post by {} id not found", postId);
            throw new RuntimeException("Post not found");
        }

        Set<String> usersLiked = post.getLikedUsers();
        Set<String> usersDisliked = post.getDislikedUsers();
        if(usersLiked.stream().anyMatch(u -> u.equals(username))){
                post.setLikes(post.getLikes()-1);
                post.getLikedUsers().remove(username);

        } else {
                post.setLikes(post.getLikes()+1);
                post.getLikedUsers().add(username);
        }

        if(usersDisliked.stream().anyMatch(u -> u.equals(username))){
            post.setDislikes(post.getDislikes()-1);
            post.getDislikedUsers().remove(username);
        }

        return postRepository.save(post);
    }

    public Post dislikePost(Long postId, String username) {
        Post post = postRepository.findPostById(postId);

        if(post == null){
            LOG.error("Post by {} id not found", postId);
            throw new RuntimeException("Post not found");
        }
        Set<String> usersLiked = post.getLikedUsers();
        Set<String> usersDisliked = post.getDislikedUsers();
        if(usersDisliked.stream().anyMatch(u -> u.equals(username))){
            post.setDislikes(post.getDislikes()-1);
            post.getDislikedUsers().remove(username);

        } else {
            post.setDislikes(post.getDislikes()+1);
            post.getDislikedUsers().add(username);
        }

        if(usersLiked.stream().anyMatch(u -> u.equals(username))){
            post.setLikes(post.getLikes()-1);
            post.getLikedUsers().remove(username);
        }

        return postRepository.save(post);
    }

    public void deletePost(Long postId){
        Post post = postRepository.findPostById(postId);
        postRepository.delete(post);
    }

    private Set<String> getTagsFromStr(String str){
        String[] tags = str.split("&;");
        return new HashSet<>(Arrays.asList(tags));
    }

    public List<Post> getByTagPost(String tagName) {
        List<Post> posts = postRepository.findAllByOrderByCreatedDateDesc();
        if(posts.size() == 0){
            LOG.info("No one post in repository");
        }

        List<Post> postsByTag = new ArrayList<>();

        for (int i = 0; i < 50 && i < posts.size(); i++){
            if(posts.get(i).getTags().stream().anyMatch(t -> t.equals(tagName) )) {
                postsByTag.add(posts.get(i));
            }
        }

        if(posts.size() == 0){
            LOG.info("No one post in repository by tag {}", tagName);
        }

        return postsByTag;
    }
}

package com.example.demo.controllers;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/post")
public class PostController {
    Logger LOG = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public String show(@PathVariable("postId") long postId, Model model) throws IOException {

        buildModel(postId, model);

        return "post";
    }

    @PostMapping("/{postId}/addcomment")
    public String createComment(@Valid CommentDTO commentDTO, @PathVariable("postId") long postId,
                                BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("formError", "Bad input value");
            LOG.error("Comment form errors {}", bindingResult.getAllErrors());
            return "addPost";
        }

        commentService.createComment(postId, commentDTO);

        return "redirect:/post/" + postId;
    }

    @GetMapping("/addpost")
    public String addPost(Model model){
        PostDTO postDTO = new PostDTO();
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("postDTO", postDTO);
        model.addAttribute("userDetails", userDetails);

        return "addpost";
    }

    @PostMapping("/addpost")
    public String createPost(@Valid PostDTO postDTO,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("formError", "Bad input value");
            LOG.error("Post form errors {}", bindingResult.getAllErrors());
            return "addPost";
        }
        Post post = postService.createPost(postDTO);
        return "redirect:/post/" + post.getId();
    }


    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable("id") Long postId){
        postService.deletePost(postId);

        return "redirect:/";
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable("postId") Long postId,
                           @Valid PostDTO postDTO,
                           Model model){

        String likedUser = postDTO.getMarkedUsername();
        Post post = postService.likePost(postId, likedUser);

        return "redirect:/post/" + postId;
    }

    @PostMapping("/{postId}/dislike")
    public String dislikePost(@PathVariable("postId") long postId,
                              @Valid PostDTO postDTO,
                              Model model){
        String likedUser = postDTO.getMarkedUsername();
        Post post = postService.dislikePost(postId, likedUser);

        return "redirect:/post/" + postId;
    }


    private void buildModel(long postId, Model model) {
        Post post = postService.getPostById(postId);

        if(post == null){
            throw new RuntimeException("Post not found");
        }

        PostDTO postDTO = new PostDTO();
        CommentDTO commentDTO = new CommentDTO();
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Comment> comments = commentService.getAllCommentsForPost(postId);

        model.addAttribute("post", post);
        model.addAttribute("postDTO", postDTO);
        model.addAttribute("commentDTO", commentDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("image", post.getEncodedImage());
        model.addAttribute("userDetails", userDetails);


        if(post.getLikedUsers().stream().anyMatch(u -> u.equals("noname"))){
            model.addAttribute("bLiked", true);
        } else {
            model.addAttribute("bLiked", false);
        }
        if(post.getDislikedUsers().stream().anyMatch(u -> u.equals("noname"))) {
            model.addAttribute("bDisliked", true);
        } else {
            model.addAttribute("bDisliked", false);
        }

        //return model;
    }


}

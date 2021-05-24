package com.example.demo.controllers;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class LentaController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String newsPage(Model model){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts = postService.getLastPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("userDetails", userDetails);

        System.out.println(userDetails.getAuthorities());

        return "lenta";
    }

    @GetMapping("/search/{tagName}")
    public String pageByTag(@PathVariable("tagName") String tagName, Model model){
        List<Post> posts = postService.getByTagPost(tagName);
        model.addAttribute("posts", posts);
        model.addAttribute("searchTag", tagName);
        return "lenta";
    }
}

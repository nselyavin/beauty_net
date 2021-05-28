package com.example.demo.controllers;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String newsPage(Model model){
        List<Post> posts = postService.getLastPosts();
        model.addAttribute("posts", posts);
        return "lenta";
    }

}

package com.Blog.Blogging.controller;

import com.Blog.Blogging.model.post;
import com.Blog.Blogging.model.post;
import com.Blog.Blogging.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostRepo postRepo;

    // Home page showing all posts
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listPost", postRepo.findAll());
        return "index";
    }

    // Show form to create a new post
    @GetMapping("/new")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new post());
        return "new_post";
    }

    // Save a new post (use POST method)
    @PostMapping("/save")
    public String savePost(@ModelAttribute("post") post post) {
        postRepo.save(post);
        return "redirect:/";
    }

    // Show form to edit an existing post
    @GetMapping("/edit/{id}")
    public String showEditPostForm(@PathVariable("id") int id, Model model) {
        Optional<post> post = postRepo.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "edit_post";
        } else {
            return "redirect:/"; // You can redirect to an error page if needed
        }
    }

    // Update a post (use POST method)
    @PostMapping("/update")
    public String updatePost(@ModelAttribute("post") post post) {
        postRepo.save(post);
        return "redirect:/";
    }

    // Delete a post by ID
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") int id) {
        postRepo.deleteById(id);
        return "redirect:/";
    }
}

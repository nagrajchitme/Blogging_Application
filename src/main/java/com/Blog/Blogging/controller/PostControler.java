package com.Blog.Blogging.controller;

import com.Blog.Blogging.model.post;
import com.Blog.Blogging.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostControler {

    @Autowired
    PostRepo repo;

    @GetMapping("/")
   public String viewHomePage(Model model){
       model.addAttribute("listPost",repo.findAll());
       return "index";
   }

   @GetMapping("/new")
   public String newPost(Model model){
        model.addAttribute("post",new post());
        return "new_post";
   }

    @GetMapping("/save")
    public String savePost(@ModelAttribute("post") post post){
        repo.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int id,Model model){
        model.addAttribute("post", repo.findById(id));
        return "edit_post";
    }

    @GetMapping("/update")
    public String updatePost(@ModelAttribute("post") post post){
        repo.save(post);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/";
    }
}

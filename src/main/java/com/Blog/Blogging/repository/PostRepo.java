package com.Blog.Blogging.repository;


import com.Blog.Blogging.model.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<post,Integer>{

}

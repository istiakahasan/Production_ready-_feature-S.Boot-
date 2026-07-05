package com.example.pro_ready_features.controllers;

import com.example.pro_ready_features.dto.PostDto;
import com.example.pro_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById();

    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost){
        return postService.createNewPost(inputPost);
    }


    @PutMapping("{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPost,@PathVariable Long postId){
        return postService.updatePost(inputPost,postId);
    }
}

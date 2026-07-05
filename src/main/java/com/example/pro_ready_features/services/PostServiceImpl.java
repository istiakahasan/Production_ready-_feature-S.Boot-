package com.example.pro_ready_features.services;

import com.example.pro_ready_features.dto.PostDto;
import com.example.pro_ready_features.entities.PostEntity;
import com.example.pro_ready_features.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@RequiredArgsConstructor
@Service
public class PostServiceImpl {
     private final ModelMapper mapper;
    private final PostRepository postRepository;



    public PostDto createNewPost(PostDto inputPost){
        PostEntity postEntity=mapper.map(inputPost, PostEntity.class);
        return mapper.map(postRepository.save(postEntity),PostDto.class);
    }

    public PostDto getPostById(Long postId){
    PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ConfigDataResourceNotFoundException(postId));
    return mapper.map(postEntity,PostDto.class);
    }

    public PostDto updatePost(PostDto inputPost,Long postId){
    PostEntity oldPost=postRepository.findById(postId).orElseThrow(()->new ResolutionException(postId));
    inputPost.setId(postId);
    mapper.map(inputPost,oldPost);
    return mapper.map(postRepository.save(oldPost),PostDto.class);
    }
}

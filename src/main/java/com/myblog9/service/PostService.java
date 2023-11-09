package com.myblog9.service;

import com.myblog9.payload.PostDto;
import com.myblog9.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    public void deletePostById(Long id);

    PostDto getPostById(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
   }

package com.myblog9.service.impl;

import com.myblog9.entity.Post;
import com.myblog9.exception.ResourceNotFound;
import com.myblog9.payload.PostDto;
import com.myblog9.payload.PostResponse;
import com.myblog9.repository.PostRepository;
import com.myblog9.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo,ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }

    PostDto mapToDto(Post savedpost) {
        PostDto postDto = modelMapper.map(savedpost, PostDto.class);
//        postDto.setId(savedpost.getId());
//        postDto.setTitle(savedpost.getTitle());
//        postDto.setContent(savedpost.getContent());
//        postDto.setDescription(savedpost.getDescription());
        return postDto;
    }

    Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        //Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        return post;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public void deletePostById(Long id) {
        postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with ID:" + id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with ID:" + id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with ID:" + id)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post saveedPost = postRepo.save(post);
        PostDto dto = mapToDto(saveedPost);
        return dto;
    }

    @Override
    public  PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest pageble = PageRequest.of(pageNo,pageSize,sort);
         Page<Post> pagePostObjects = postRepo.findAll(pageble);
        List<Post> posts = pagePostObjects.getContent();

        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse response=new PostResponse();
        response.setDto(dtos);
        response.setPageNo(pagePostObjects.getNumber());
        response.setTotalPages(pagePostObjects.getTotalPages());
        response.setLastPage(pagePostObjects.isLast());
        response.setPageSize(pagePostObjects.getSize());


        return response;
    }
}


























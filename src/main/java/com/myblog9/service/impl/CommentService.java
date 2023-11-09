package com.myblog9.service.impl;

import com.myblog9.entity.Comment;
import com.myblog9.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    public CommentDto createComment(long postID, CommentDto commentDto);

    public void deleteCommentById(long postId, long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto updateComment(long commentId, CommentDto commentDto);
}

package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;

public interface CommentService {
    ResponseCreateComment createComment(Long memberId, RequestCreateComment commentDTO);
    void deleteComment(Long commentId);
}

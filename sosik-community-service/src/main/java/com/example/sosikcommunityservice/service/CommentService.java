package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.request.RequestUpdateComment;

import java.util.List;

public interface CommentService {
    RequestCreateComment createComment(Long memberId,RequestCreateComment commentDTO);
}

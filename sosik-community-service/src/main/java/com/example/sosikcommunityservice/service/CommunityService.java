package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;

public interface CommunityService {
    String createPost(Long memberId, RequestCreatePost communityDTO);
}

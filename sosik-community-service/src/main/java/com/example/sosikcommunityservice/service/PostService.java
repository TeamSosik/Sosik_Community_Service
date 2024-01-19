package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;

import java.util.List;

public interface PostService {
    String createPost(Long memberId, RequestCreatePost postDTO);

    List<ResponseGetPostList> getPostList();

    ResponseGetPost getPost(Long postId);

    Void updatePost(Long postId, RequestUpdatePost updatepost);

    String deletePost(Long postId);
}

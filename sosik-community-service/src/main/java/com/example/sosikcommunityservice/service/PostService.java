package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;

import java.util.List;

public interface PostService {
    void createPost(Long memberId, RequestCreatePost postDTO);

    List<ResponseGetPostList> getPostList();

    ResponseGetPost getPost(Long postId);

    void updatePost(Long postId, RequestUpdatePost updatepost);

    void deletePost(Long postId);
}

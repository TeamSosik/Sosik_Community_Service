package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;

import java.util.List;

public interface PostService {
    List<ResponseGetPostList> getPostList();
    ResponseGetPost getPost(Long postId);
}

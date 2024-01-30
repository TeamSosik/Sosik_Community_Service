package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.GetPostSliceCondition;
import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostService {
    void createPost(Long memberId, RequestCreatePost postDTO);
    ResponseGetPost getPost(Long postId);
    Slice<ResponseGetPostList> getPostListSlice(GetPostSliceCondition condition);
    Slice<ResponseGetPostList> getPostListSliceSearch(GetPostSliceCondition condition);
    void updatePost(Long postId, RequestUpdatePost updatepost);
    void deletePost(Long postId);
}

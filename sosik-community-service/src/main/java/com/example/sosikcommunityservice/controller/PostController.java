package com.example.sosikcommunityservice.controller;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import com.example.sosikcommunityservice.dto.response.Result;
import com.example.sosikcommunityservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/v1")
@CrossOrigin
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public Result<Void> createPost(@RequestHeader Long memberId, @RequestBody RequestCreatePost post) {
        postService.createPost(memberId, post);
        return Result.success();
    }

    @GetMapping
    public Result<List<ResponseGetPostList>> getPostList() {
        List<ResponseGetPostList> responseGetPostList = postService.getPostList();
        return Result.success(responseGetPostList);
    }

    @GetMapping("/{postId}")
    public Result<ResponseGetPost> getPost(@PathVariable Long postId) {
        ResponseGetPost responseGetpost = postService.getPost(postId);
        return Result.success(responseGetpost);
    }

    @PatchMapping("/{postId}")
    public Result<Void> updatePost(@PathVariable Long postId, @RequestBody final RequestUpdatePost updatepost) {
        postService.updatePost(postId, updatepost);
        return Result.success();
    }

    @DeleteMapping("/{postId}")
    public Result<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return Result.success();
    }
}
package com.example.sosikcommunityservice.controller;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.response.Result;
import com.example.sosikcommunityservice.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community/v1")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/createwrite")
    public Result<Void> createPost(@RequestHeader Long memberId, @RequestBody RequestCreatePost community) {
        communityService.createPost(memberId, community);
        return Result.success();
    }
}

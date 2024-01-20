package com.example.sosikcommunityservice.controller;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.response.Result;
import com.example.sosikcommunityservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/v1")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public Result<Void> createComment(@RequestHeader Long memberId,@RequestBody RequestCreateComment comment) {
        commentService.createComment(memberId,comment);
        return Result.success();
    }

}

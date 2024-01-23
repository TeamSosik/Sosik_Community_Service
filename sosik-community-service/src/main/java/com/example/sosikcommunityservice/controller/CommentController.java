package com.example.sosikcommunityservice.controller;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;
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
    public Result<ResponseCreateComment> createComment(@RequestHeader Long memberId, @RequestBody RequestCreateComment comment) {
        ResponseCreateComment responseCreateComment = commentService.createComment(memberId,comment);
        return Result.success(responseCreateComment);
    }

    @DeleteMapping("/{commentId}")
    public Result<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return Result.success();
    }

}

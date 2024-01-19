package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetPostList(
        Long memberId,
        String title,
        Long hits,
        LocalDateTime createdAt
   ) {
}
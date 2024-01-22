package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetPostList(
        Long id,
        Long memberId,
        String title,
        Long hits,
        Integer commentCount,
        LocalDateTime createdAt
   ) {
}

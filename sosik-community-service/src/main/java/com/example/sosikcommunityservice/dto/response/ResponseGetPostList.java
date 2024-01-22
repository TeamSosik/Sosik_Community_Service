package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetPostList(
        Long id,
        Long memberId,
        String title,
        Integer hits,
        Integer commentCount,
        LocalDateTime createdAt
) {
}

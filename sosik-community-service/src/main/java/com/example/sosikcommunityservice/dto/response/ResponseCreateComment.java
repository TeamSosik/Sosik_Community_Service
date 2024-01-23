package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseCreateComment(
        Long id,
        Long memberId,
        String content,
        LocalDateTime createdAt
) {
}
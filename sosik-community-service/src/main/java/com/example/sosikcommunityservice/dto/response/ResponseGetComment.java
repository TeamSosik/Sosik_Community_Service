package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetComment(
        Long communityId,
        Long memberId,
        String content,
        LocalDateTime createdAt
) {
}

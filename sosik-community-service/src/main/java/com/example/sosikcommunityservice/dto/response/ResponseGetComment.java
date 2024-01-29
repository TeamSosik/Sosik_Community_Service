package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetComment(
        Long id,
        Long communityId,
        String nickname,
        Long memberId,
        String content,
        LocalDateTime createdAt
) {
}

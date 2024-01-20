package com.example.sosikcommunityservice.dto.request;

import lombok.Builder;

@Builder
public record RequestCreateComment(
        Long communityId,
        String content
) {

}
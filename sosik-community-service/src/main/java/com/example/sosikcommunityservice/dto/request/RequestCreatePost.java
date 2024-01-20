package com.example.sosikcommunityservice.dto.request;

import lombok.Builder;

@Builder
public record RequestCreatePost(
        Long memberId,
        String title,
        String content
) {

}

package com.example.sosikcommunityservice.dto.request;

import lombok.Builder;

@Builder
public record RequestUpdatePost(
        Long memberId,
        String title,
        String content
) {

}

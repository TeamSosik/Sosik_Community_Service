package com.example.sosikcommunityservice.dto.request;

import lombok.Builder;

@Builder
public record RequestUpdateComment(
        String content
) {

}
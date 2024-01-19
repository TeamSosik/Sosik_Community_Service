package com.example.sosikcommunityservice.dto.request;

import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
public record RequestCreatePost(
        Long memberId,
        String title,
        String content
) {

}

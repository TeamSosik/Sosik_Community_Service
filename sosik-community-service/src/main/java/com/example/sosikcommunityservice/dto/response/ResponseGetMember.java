package com.example.sosikcommunityservice.dto.response;

import lombok.Builder;

@Builder
public record ResponseGetMember(
        String nickname,
        String profileImage) {

}

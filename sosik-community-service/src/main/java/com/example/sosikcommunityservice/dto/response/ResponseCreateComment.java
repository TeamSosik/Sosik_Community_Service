package com.example.sosikcommunityservice.dto.response;

import com.example.sosikcommunityservice.model.entity.CommentEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseCreateComment(
        Long id,
        Long memberId,
        String nickname,
        String profileImage,
        String content,
        LocalDateTime createdAt
) {
    public static ResponseCreateComment responseCreate(CommentEntity commentEntity, ResponseGetMember responseGetMember) {
        return ResponseCreateComment.builder()
                .id(commentEntity.getId())
                .memberId(commentEntity.getMemberId())
                .nickname(responseGetMember.nickname())
                .profileImage(responseGetMember.profileImage())
                .content(commentEntity.getContent())
                .createdAt(commentEntity.getCreatedAt())
                .build();
    }
}
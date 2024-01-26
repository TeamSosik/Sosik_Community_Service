package com.example.sosikcommunityservice.dto.response;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetPostList(
        Long id,
        Long memberId,
        String title,
        Integer hits,
        Integer commentCount,
        LocalDateTime createdAt
) {
    public static ResponseGetPostList responseGetPostList(PostEntity postEntity ){
        return  new ResponseGetPostList(
                postEntity.getId(),
                postEntity.getMemberId(),
                postEntity.getTitle(),
                postEntity.getHits(),
                postEntity.getComments().size(),
                postEntity.getCreatedAt()
        );
    }
}

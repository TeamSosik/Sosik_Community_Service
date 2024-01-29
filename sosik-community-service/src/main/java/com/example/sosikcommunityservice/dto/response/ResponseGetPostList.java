package com.example.sosikcommunityservice.dto.response;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import lombok.Builder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Builder
public record ResponseGetPostList(
        Long id,
        Long memberId,
        String nickname,
        String title,
        Integer hits,
        Integer commentCount,
        LocalDateTime createdAt
) {
    public static ResponseGetPostList responseGetPostList(PostEntity postEntity){
        String finalUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/members/v1/"+postEntity.getMemberId())
                .build()
                .toUriString();
        WebClient webClient = WebClient.create();
        // GET 요청 보내기
        String nickname = webClient.get()
                .uri(finalUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return  new ResponseGetPostList(
                postEntity.getId(),
                postEntity.getMemberId(),
                nickname,
                postEntity.getTitle(),
                postEntity.getHits(),
                postEntity.getComments().size(),
                postEntity.getCreatedAt()
        );
    }
}

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
        String profileImage,
        String title,
        Integer hits,
        Integer commentCount,
        LocalDateTime createdAt
) {
    public static ResponseGetPostList responseGetPostList(PostEntity postEntity){
        String finalUrl = UriComponentsBuilder.fromHttpUrl("http://43.200.224.252:9000/members/v1/"+postEntity.getMemberId())
                .build()
                .toUriString();
        WebClient webClient = WebClient.create();
        // GET 요청 보내기
        ResponseGetMember responseGetMember = webClient.get()
                .uri(finalUrl)
                .retrieve()
                .bodyToMono(ResponseGetMember.class)
                .block();
        return  new ResponseGetPostList(
                postEntity.getId(),
                postEntity.getMemberId(),
                responseGetMember.nickname(),
                responseGetMember.profileImage(),
                postEntity.getTitle(),
                postEntity.getHits(),
                postEntity.getComments().size(),
                postEntity.getCreatedAt()
        );
    }
}

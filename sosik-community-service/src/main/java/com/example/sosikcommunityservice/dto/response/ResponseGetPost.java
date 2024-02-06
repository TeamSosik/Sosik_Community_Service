package com.example.sosikcommunityservice.dto.response;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import lombok.Builder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ResponseGetPost(
        Long memberId,
        String nickname,
        String profileImage,
        String title,
        String content,
        Integer hits,
        LocalDateTime createdAt,
        List<ResponseGetComment> comments

   ) {

   public static ResponseGetPost responseGetPost(PostEntity postEntity, ResponseGetMember responseGetMember) {
      return new ResponseGetPost(
              postEntity.getMemberId(),
              responseGetMember.nickname(),
              responseGetMember.profileImage(),
              postEntity.getTitle(),
              postEntity.getContent(),
              postEntity.increase(postEntity.getHits()),
              postEntity.getCreatedAt(),
              postEntity.getComments().stream()
                      .map(commentEntity -> {
                         String finalUrl = UriComponentsBuilder.fromHttpUrl("http://43.200.224.252:9000/members/v1/"+commentEntity.getMemberId())
                                 .build()
                                 .toUriString();
                         WebClient webClient = WebClient.create();
                         // GET 요청 보내기
                         ResponseGetMember leaveMember = webClient.get()
                                 .uri(finalUrl)
                                 .retrieve()
                                 .bodyToMono(ResponseGetMember.class)
                                 .block();
                         return ResponseGetComment.builder()
                                 .id(commentEntity.getId())
                                 .communityId(postEntity.getId())
                                 .nickname(leaveMember.nickname())
                                 .profileImage(leaveMember.profileImage())
                                 .memberId(commentEntity.getMemberId())
                                 .content(commentEntity.getContent())
                                 .createdAt(commentEntity.getCreatedAt())
                                 .build();
                      })
                      .collect(Collectors.toList())
      );
   }

}

package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.request.RequestUpdateComment;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetMember;
import com.example.sosikcommunityservice.exception.ApplicationException;
import com.example.sosikcommunityservice.exception.ErrorCode;
import com.example.sosikcommunityservice.model.entity.CommentEntity;
import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.repository.CommentRepository;
import com.example.sosikcommunityservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public ResponseCreateComment createComment(Long memberId, RequestCreateComment commentDTO) {
        PostEntity postEntity = postRepository.findById(commentDTO.communityId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND));

        CommentEntity commentEntity = CommentEntity.create(commentDTO, postEntity, memberId);
        commentRepository.save(commentEntity);

        String finalUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:9000/members/v1/"+memberId)
                .build()
                .toUriString();
        WebClient webClient = WebClient.create();

        // GET 요청 보내기
        ResponseGetMember responseGetMember = webClient.get()
                .uri(finalUrl)
                .retrieve()
                .bodyToMono(ResponseGetMember.class)
                .block();

        return ResponseCreateComment.responseCreate(commentEntity,responseGetMember);
    }

    @Transactional
    @Override
    public void updateComment(Long commentId, RequestUpdateComment commentDTO) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        commentEntity.updateComment(commentDTO);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.COMMENT_NOT_FOUND));

        commentRepository.deleteById(commentId);
    }
}

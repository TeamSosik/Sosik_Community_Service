package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;
import com.example.sosikcommunityservice.exception.ApplicationException;
import com.example.sosikcommunityservice.exception.ErrorCode;
import com.example.sosikcommunityservice.model.entity.CommentEntity;
import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.repository.CommentRepository;
import com.example.sosikcommunityservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Transactional
    @Override
    public ResponseCreateComment createComment(Long memberId, RequestCreateComment commentDTO) {
        PostEntity postEntity = postRepository.findById(commentDTO.communityId()).orElseThrow(() -> {
            return new ApplicationException(ErrorCode.POST_NOT_FOUND);
        });
        CommentEntity commentEntity = CommentEntity.create(commentDTO, postEntity, memberId);

        commentRepository.save(commentEntity);

        ResponseCreateComment responseComment = CommentEntity.responseCreate(commentEntity);

        return responseComment;
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

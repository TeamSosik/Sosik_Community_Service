package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.response.ResponseGetComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import com.example.sosikcommunityservice.model.entity.CommentEntity;
import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public List<ResponseGetPostList> getPostList() {
        List<PostEntity> postEntities = postRepository.findAll();

        return postEntities.stream()
                .map(entity -> new ResponseGetPostList(
                        entity.getMemberId(),
                        entity.getTitle(),
                        entity.getHits(),
                        entity.getCreatedAt()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseGetPost getPost(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);

        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();

            return new ResponseGetPost(
                    postEntity.getMemberId(),
                    postEntity.getTitle(),
                    postEntity.getContent(),
                    postEntity.getHits(),
                    postEntity.getCreatedAt(),
                    postEntity.getComments().stream()
                            .map(commentEntity -> {
                                return ResponseGetComment.builder()
                                        .communityId(postEntity.getId())
                                        .memberId(commentEntity.getMemberId())
                                        .content(commentEntity.getContent())
                                        .createdAt(commentEntity.getCreatedAt())
                                        .build();
                            })
                            .collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }



}

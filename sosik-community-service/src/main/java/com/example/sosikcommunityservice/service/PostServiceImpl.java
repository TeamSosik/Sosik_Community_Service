package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseGetComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import com.example.sosikcommunityservice.exception.ApplicationException;
import com.example.sosikcommunityservice.exception.ErrorCode;
import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void createPost(Long memberId, RequestCreatePost postDTO) {
        PostEntity postentity = PostEntity.builder()
                .memberId(postDTO.memberId())
                .title(postDTO.title())
                .content(postDTO.content())
                .hits(0)
                .build();
        postRepository.save(postentity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGetPostList> getPostList() {
        List<PostEntity> postEntities = postRepository.findAllByOrderByCreatedAtDesc();

        return postEntities.stream()
                .map(entity -> new ResponseGetPostList(
                        entity.getId(),
                        entity.getMemberId(),
                        entity.getTitle(),
                        entity.getHits(),
                        entity.getComments().size(),
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

    @Override
    public void updatePost(Long postId, RequestUpdatePost updatePost) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> {
            return new ApplicationException(ErrorCode.UNAUTHORIZED_UPDATE);
        });
        postEntity.updatePost(updatePost);
    }

    @Override
    public void deletePost(Long postId) {
        if (postRepository.findById(postId) == null) {
            throw new ApplicationException(ErrorCode.POST_NOT_FOUND);
        }
        postRepository.deleteById(postId);
    }


}

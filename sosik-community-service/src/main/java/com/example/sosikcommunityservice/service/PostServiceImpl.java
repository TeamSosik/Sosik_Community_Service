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

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public String createPost(Long memberId, RequestCreatePost postDTO) {
        PostEntity postentity = PostEntity.builder()
                .memberId(postDTO.memberId())
                .title(postDTO.title())
                .content(postDTO.content())
                .build();
        postRepository.save(postentity);
        return "게시글 등록이 완료 되었습니다.";
    }

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

    @Override
    @Transactional
    public Void updatePost(Long postId, RequestUpdatePost updatePost) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);


//        if (postEntityOptional.isPresent()) {
//            PostEntity postEntity = postEntityOptional.get();
//
//            if (updatePost.title() != null) {
//                postEntity.setTitle(updatePost.title());
//            }
//        }
        return null;
    }

    @Override
    public String deletePost(Long postId) {
        if (postRepository.findById(postId) == null) {
            throw new ApplicationException(ErrorCode.POST_NOT_FOUND);
        }
        postRepository.deleteById(postId);
        return "게시글 삭제가 완료 되었습니다.";
    }


}

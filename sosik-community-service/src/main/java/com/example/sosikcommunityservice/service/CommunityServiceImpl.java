package com.example.sosikcommunityservice.service;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.model.entity.CommunityEntity;
import com.example.sosikcommunityservice.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Override
    public String createPost(Long memberId, RequestCreatePost communityDTO) {
        CommunityEntity communityEntity = CommunityEntity.builder()
                .memberId(communityDTO.memberId())
                .title(communityDTO.title())
                .content(communityDTO.content())
                .build();
        communityRepository.save(communityEntity);
        return "게시글 등록이 완료되었습니다.";
    }
}

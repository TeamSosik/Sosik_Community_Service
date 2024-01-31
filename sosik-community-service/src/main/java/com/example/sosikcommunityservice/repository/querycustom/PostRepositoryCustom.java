package com.example.sosikcommunityservice.repository.querycustom;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostEntity> findByContentOrderByIdDesc(String content, Pageable pageable);
}

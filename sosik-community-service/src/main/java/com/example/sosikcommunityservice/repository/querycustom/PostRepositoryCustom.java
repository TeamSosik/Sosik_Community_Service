package com.example.sosikcommunityservice.repository.querycustom;

import com.example.sosikcommunityservice.dto.request.GetPostSliceCondition;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import com.example.sosikcommunityservice.model.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostEntity> findByContentOrderByIdDesc(String content, Pageable pageable);
}

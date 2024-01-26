package com.example.sosikcommunityservice.repository;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.model.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByOrderByCreatedAtDesc();
    Slice<PostEntity> findAllByOrderByIdDesc(Pageable pageable);
}
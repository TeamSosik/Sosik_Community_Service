package com.example.sosikcommunityservice.repository;

import com.example.sosikcommunityservice.model.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByCommunityId(Long communityId);
}
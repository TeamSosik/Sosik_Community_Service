package com.example.sosikcommunityservice.repository;

import com.example.sosikcommunityservice.model.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
}

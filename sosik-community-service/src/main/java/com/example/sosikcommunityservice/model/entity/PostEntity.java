package com.example.sosikcommunityservice.model.entity;

import com.example.sosikcommunityservice.dto.request.RequestCreatePost;
import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "community",
        indexes = {
                @Index(
                        name = "idx_community_title", columnList = "title", unique = false
                ),
                @Index(
                        name = "idx_community_writer", columnList = "memberId", unique = false
                )
        }
)

public class PostEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    private Integer hits;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Builder
    public PostEntity(
            final Long id,
            final Long memberId,
            final String title,
            final String content,
            final Integer hits,
            final List<CommentEntity> comments
    ) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.comments = comments;
    }

    public void updatePost(RequestUpdatePost requestUpdatePost) {
        this.title = requestUpdatePost.title();
        this.content = requestUpdatePost.content();
    }

    public Integer increase(Integer hits) {
        this.hits = hits + 1;
        return hits;
    }

    public static PostEntity create(Long memberId, RequestCreatePost postDTO) {
        return PostEntity.builder()
                .memberId(memberId)
                .title(postDTO.title())
                .content(postDTO.content())
                .hits(0)
                .build();
    }

}

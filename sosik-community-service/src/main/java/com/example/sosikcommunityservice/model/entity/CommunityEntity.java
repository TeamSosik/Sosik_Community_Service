package com.example.sosikcommunityservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
                        name = "idx_community_content", columnList = "content", unique = false
                ),
                @Index(
                        name = "idx_community_writer", columnList = "memberId", unique = false
                )
        }
)
// 작성자,내용,제목
public class CommunityEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 255,nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    private String content;

    private Long hits;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Builder
    public CommunityEntity(
            final Long id,
            final Long memberId,
            final String title,
            final String content,
            final Long hits
    ) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.hits = hits;
    }
}

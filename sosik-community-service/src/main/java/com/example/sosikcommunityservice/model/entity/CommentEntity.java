package com.example.sosikcommunityservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class CommentEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private PostEntity community;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 1000, nullable = false)
    private String content;

    @Builder
    public CommentEntity(
            final Long memberId,
            final String content
    ) {
        this.memberId = memberId;
        this.content = content;

    }
}
